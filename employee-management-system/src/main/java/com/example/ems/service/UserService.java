package com.example.ems.service;

import com.example.ems.dto.LoginRequest;
import com.example.ems.dto.RegisterRequest;
import com.example.ems.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    
    UserDto registerUser(RegisterRequest registerRequest);
    
    String authenticateUser(LoginRequest loginRequest);
    
    UserDto getUserById(Long id);
    
    UserDto getUserByUsername(String username);
    
    Page<UserDto> getAllUsers(Pageable pageable);
    
    UserDto updateUser(Long id, UserDto userDto);
    
    void deleteUser(Long id);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
} 