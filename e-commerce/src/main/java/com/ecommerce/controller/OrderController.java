package com.ecommerce.controller;

import com.ecommerce.dto.MessageResponse;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.dto.OrderItemDto;
import com.ecommerce.model.*;
import com.ecommerce.repository.*;
import com.ecommerce.security.services.UserDetailsImpl;
import com.ecommerce.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PaymentService paymentService;
    
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<OrderDto>> getUserOrders(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "orderDate,desc") String[] sort) {
        
        try {
            User user = userRepository.findById(userDetails.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            
            List<Sort.Order> orders = getSortOrders(sort);
            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            
            Page<Order> ordersPage = orderRepository.findByUser(user, pageable);
            List<OrderDto> orderDtos = ordersPage.getContent().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
            
            return new ResponseEntity<>(orderDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<OrderDto> getOrderById(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("id") long id) {
        
        Order order = orderRepository.findById(id)
                .orElse(null);
        
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        // Check if the order belongs to the authenticated user or if the user is an admin
        if (!order.getUser().getId().equals(userDetails.getId()) && 
                !userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        return new ResponseEntity<>(convertToDto(order), HttpStatus.OK);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Transactional
    public ResponseEntity<OrderDto> createOrder(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody OrderDto orderDto) {
        
        try {
            User user = userRepository.findById(userDetails.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            
            // Get user's cart
            Cart cart = cartRepository.findByUser(user)
                    .orElseThrow(() -> new RuntimeException("Cart not found"));
            
            if (cart.getCartItems().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }
            
            // Create a new order
            Order order = new Order();
            order.setUser(user);
            order.setOrderDate(LocalDateTime.now());
            order.setStatus(OrderStatus.PENDING);
            order.setShippingAddress(orderDto.getShippingAddress());
            order.setShippingCity(orderDto.getShippingCity());
            order.setShippingState(orderDto.getShippingState());
            order.setShippingZip(orderDto.getShippingZip());
            order.setShippingCountry(orderDto.getShippingCountry());
            order.setPaymentMethod(orderDto.getPaymentMethod());
            
            // Add items from cart to order
            for (CartItem cartItem : cart.getCartItems()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice(cartItem.getProduct().getPrice());
                
                // Reduce product stock
                Product product = cartItem.getProduct();
                if (product.getStockQuantity() < cartItem.getQuantity()) {
                    return ResponseEntity.badRequest().body(null);
                }
                
                product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
                productRepository.save(product);
                
                order.addOrderItem(orderItem);
            }
            
            // Calculate total price
            order.setTotalPrice(order.calculateTotalPrice());
            
            // Save the order first to get an ID
            Order savedOrder = orderRepository.save(order);
            
            // Process payment
            String transactionId = paymentService.processPayment(savedOrder);
            
            if (transactionId == null) {
                // Payment failed, rollback transaction
                throw new RuntimeException("Payment processing failed");
            }
            
            // Update order with transaction ID and change status to PROCESSING
            savedOrder.setPaymentTransactionId(transactionId);
            savedOrder.setStatus(OrderStatus.PROCESSING);
            savedOrder = orderRepository.save(savedOrder);
            
            // Clear the cart
            cartItemRepository.deleteByCart(cart);
            
            return new ResponseEntity<>(convertToDto(savedOrder), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @PathVariable("id") long id,
            @RequestParam OrderStatus status) {
        
        Order order = orderRepository.findById(id)
                .orElse(null);
        
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        try {
            // If status is being changed to REFUNDED, process the refund
            if (status == OrderStatus.REFUNDED && order.getStatus() != OrderStatus.REFUNDED) {
                boolean refundSuccessful = paymentService.refundPayment(order);
                
                if (!refundSuccessful) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                order.setStatus(status);
            }
            
            Order updatedOrder = orderRepository.save(order);
            return new ResponseEntity<>(convertToDto(updatedOrder), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderDto>> getAllOrders(
            @RequestParam(required = false) OrderStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "orderDate,desc") String[] sort) {
        
        try {
            List<Sort.Order> orders = getSortOrders(sort);
            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            
            Page<Order> ordersPage;
            if (status != null) {
                ordersPage = orderRepository.findByStatus(status, pageable);
            } else {
                ordersPage = orderRepository.findAll(pageable);
            }
            
            List<OrderDto> orderDtos = ordersPage.getContent().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
            
            return new ResponseEntity<>(orderDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/{id}/refund")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderDto> refundOrder(@PathVariable("id") long id) {
        Order order = orderRepository.findById(id)
                .orElse(null);
        
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        try {
            boolean refundSuccessful = paymentService.refundPayment(order);
            
            if (refundSuccessful) {
                Order updatedOrder = orderRepository.save(order);
                return new ResponseEntity<>(convertToDto(updatedOrder), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    private List<Sort.Order> getSortOrders(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();
        
        if (sort[0].contains(",")) {
            // Will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(_sort[1].equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, _sort[0]));
            }
        } else {
            // Sort by single field
            // sortOrder=field,direction
            orders.add(new Sort.Order(sort[1].equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sort[0]));
        }
        
        return orders;
    }
    
    private OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUser().getId());
        orderDto.setUsername(order.getUser().getUsername());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setStatus(order.getStatus());
        orderDto.setShippingAddress(order.getShippingAddress());
        orderDto.setShippingCity(order.getShippingCity());
        orderDto.setShippingState(order.getShippingState());
        orderDto.setShippingZip(order.getShippingZip());
        orderDto.setShippingCountry(order.getShippingCountry());
        orderDto.setPaymentMethod(order.getPaymentMethod());
        orderDto.setPaymentTransactionId(order.getPaymentTransactionId());
        orderDto.setCreatedAt(order.getCreatedAt());
        orderDto.setUpdatedAt(order.getUpdatedAt());
        
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        for (OrderItem item : order.getOrderItems()) {
            OrderItemDto itemDto = new OrderItemDto();
            itemDto.setId(item.getId());
            itemDto.setOrderId(order.getId());
            itemDto.setProductId(item.getProduct().getId());
            itemDto.setProductName(item.getProduct().getName());
            itemDto.setQuantity(item.getQuantity());
            itemDto.setPrice(item.getPrice());
            itemDto.setSubtotal(item.getSubtotal());
            
            orderItemDtos.add(itemDto);
        }
        
        orderDto.setOrderItems(orderItemDtos);
        
        return orderDto;
    }
} 