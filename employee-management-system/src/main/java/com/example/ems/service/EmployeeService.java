package com.example.ems.service;

import com.example.ems.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {
    
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    
    EmployeeDto getEmployeeById(Long id);
    
    EmployeeDto getEmployeeByEmail(String email);
    
    Page<EmployeeDto> getAllEmployees(Pageable pageable);
    
    Page<EmployeeDto> getEmployeesByDepartment(Long departmentId, Pageable pageable);
    
    Page<EmployeeDto> searchEmployees(String firstName, String lastName, String email, Pageable pageable);
    
    List<EmployeeDto> getEmployeesBySalaryRange(BigDecimal minSalary, BigDecimal maxSalary);
    
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    
    void deleteEmployee(Long id);
    
    boolean existsByEmail(String email);
} 