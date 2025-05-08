package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Remove the lombok annotations since we're manually implementing everything
public class MessageResponse {
    private String message;
    
    // No-args constructor
    public MessageResponse() {
    }
    
    // Constructor with message parameter
    public MessageResponse(String message) {
        this.message = message;
    }
    
    // Getter and setter
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
} 