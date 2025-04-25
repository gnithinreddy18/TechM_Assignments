package com.example.ems.repository;

import com.example.ems.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    Optional<Employee> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    Page<Employee> findByDepartmentId(Long departmentId, Pageable pageable);
    
    @Query("SELECT e FROM Employee e WHERE " +
           "(:firstName IS NULL OR e.firstName LIKE %:firstName%) AND " +
           "(:lastName IS NULL OR e.lastName LIKE %:lastName%) AND " +
           "(:email IS NULL OR e.email LIKE %:email%)")
    Page<Employee> searchEmployees(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            Pageable pageable);
    
    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :minSalary AND :maxSalary")
    List<Employee> findBySalaryRange(
            @Param("minSalary") BigDecimal minSalary,
            @Param("maxSalary") BigDecimal maxSalary);
} 