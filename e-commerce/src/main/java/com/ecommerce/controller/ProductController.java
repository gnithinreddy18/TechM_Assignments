package com.ecommerce.controller;

import com.ecommerce.dto.MessageResponse;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.model.Tag;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.TagRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        try {
            List<Sort.Order> orders = getSortOrders(sort);
            
            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            
            Page<Product> productsPage;
            if (name == null) {
                productsPage = productRepository.findAll(pageable);
            } else {
                productsPage = productRepository.findByNameContainingIgnoreCase(name, pageable);
            }

            List<ProductDto> productDtos = productsPage.getContent().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(productDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") long id) {
        Product product = productRepository.findById(id)
                .orElse(null);

        if (product != null) {
            return new ResponseEntity<>(convertToDto(product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        try {
            Product product = convertToEntity(productDto);
            Product savedProduct = productRepository.save(product);
            return new ResponseEntity<>(convertToDto(savedProduct), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") long id, @Valid @RequestBody ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElse(null);

        if (existingProduct != null) {
            try {
                productDto.setId(id);
                Product updatedProduct = convertToEntity(productDto);
                Product savedProduct = productRepository.save(updatedProduct);
                return new ResponseEntity<>(convertToDto(savedProduct), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteProduct(@PathVariable("id") long id) {
        try {
            productRepository.deleteById(id);
            return ResponseEntity.ok(new MessageResponse("Product deleted successfully!"));
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new MessageResponse("Failed to delete product: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(
            @PathVariable("categoryId") long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Category category = categoryRepository.findById(categoryId)
                .orElse(null);
                
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Product> productsPage = productRepository.findByCategory(category, pageable);

            List<ProductDto> productDtos = productsPage.getContent().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(productDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Sort.Order> getSortOrders(String[] sort) {
        List<Sort.Order> orders = new java.util.ArrayList<>();

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

    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setImageUrl(product.getImageUrl());
        
        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }
        
        if (product.getTags() != null && !product.getTags().isEmpty()) {
            Set<Long> tagIds = product.getTags().stream()
                    .map(Tag::getId)
                    .collect(Collectors.toSet());
            dto.setTagIds(tagIds);
            
            Set<String> tagNames = product.getTags().stream()
                    .map(Tag::getName)
                    .collect(Collectors.toSet());
            dto.setTagNames(tagNames);
        }
        
        return dto;
    }

    private Product convertToEntity(ProductDto dto) {
        Product product = new Product();
        if (dto.getId() != null) {
            product.setId(dto.getId());
        }
        
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setImageUrl(dto.getImageUrl());
        
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }
        
        if (dto.getTagIds() != null && !dto.getTagIds().isEmpty()) {
            Set<Tag> tags = new HashSet<>();
            for (Long tagId : dto.getTagIds()) {
                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(() -> new RuntimeException("Tag not found"));
                tags.add(tag);
            }
            product.setTags(tags);
        }
        
        return product;
    }
} 