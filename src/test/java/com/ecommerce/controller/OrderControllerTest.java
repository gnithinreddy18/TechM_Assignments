package com.ecommerce.controller;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.model.*;
import com.ecommerce.repository.*;
import com.ecommerce.security.services.UserDetailsImpl;
import com.ecommerce.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private OrderController orderController;

    private User testUser;
    private Order testOrder;
    private UserDetailsImpl userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create test user
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setFirstName("Test");
        testUser.setLastName("User");

        // Create test order
        testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setUser(testUser);
        testOrder.setOrderDate(LocalDateTime.now());
        testOrder.setTotalPrice(new BigDecimal("100.00"));
        testOrder.setStatus(OrderStatus.PROCESSING);
        testOrder.setShippingAddress("123 Test St");
        testOrder.setShippingCity("Test City");
        testOrder.setShippingState("TS");
        testOrder.setShippingZip("12345");
        testOrder.setShippingCountry("Test Country");
        testOrder.setPaymentMethod("Credit Card");
        testOrder.setPaymentTransactionId("test-transaction-id");
        testOrder.setCreatedAt(LocalDateTime.now());
        testOrder.setUpdatedAt(LocalDateTime.now());

        // Create user details
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        userDetails = new UserDetailsImpl(1L, "testuser", "test@example.com", "password", authorities);
    }

    @Test
    void getUserOrdersShouldReturnUserOrders() {
        // Given
        List<Order> orders = Collections.singletonList(testOrder);
        Page<Order> orderPage = new PageImpl<>(orders);
        
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(testUser));
        when(orderRepository.findByUser(any(User.class), any(Pageable.class))).thenReturn(orderPage);

        // When
        ResponseEntity<List<OrderDto>> response = orderController.getUserOrders(userDetails, 0, 10, new String[]{"orderDate", "desc"});

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(testOrder.getId(), response.getBody().get(0).getId());
        assertEquals(testUser.getId(), response.getBody().get(0).getUserId());
        assertEquals(testUser.getUsername(), response.getBody().get(0).getUsername());
    }

    @Test
    void getOrderByIdShouldReturnOrder() {
        // Given
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(testOrder));

        // When
        ResponseEntity<OrderDto> response = orderController.getOrderById(userDetails, 1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testOrder.getId(), response.getBody().getId());
        assertEquals(testUser.getId(), response.getBody().getUserId());
        assertEquals(testUser.getUsername(), response.getBody().getUsername());
    }

    @Test
    void getOrderByIdShouldReturnNotFoundForNonexistentOrder() {
        // Given
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        ResponseEntity<OrderDto> response = orderController.getOrderById(userDetails, 999L);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateOrderStatusShouldUpdateStatus() {
        // Given
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(testOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        // When
        ResponseEntity<OrderDto> response = orderController.updateOrderStatus(1L, OrderStatus.SHIPPED);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void refundOrderShouldProcessRefund() {
        // Given
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(testOrder));
        when(paymentService.refundPayment(any(Order.class))).thenReturn(true);
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        // When
        ResponseEntity<OrderDto> response = orderController.refundOrder(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
} 