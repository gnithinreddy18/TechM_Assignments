package com.example.ems.service;

import com.example.ems.dto.DepartmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    
    DepartmentDto getDepartmentById(Long id);
    
    DepartmentDto getDepartmentByName(String name);
    
    Page<DepartmentDto> getAllDepartments(Pageable pageable);
    
    List<DepartmentDto> getAllDepartments();
    
    DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto);
    
    void deleteDepartment(Long id);
    
    boolean existsByName(String name);
} 