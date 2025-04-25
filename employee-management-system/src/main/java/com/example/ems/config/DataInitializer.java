package com.example.ems.config;

import com.example.ems.model.Department;
import com.example.ems.model.Employee;
import com.example.ems.model.User;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@Configuration
@Profile("dev") // Only run in development mode
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // Only initialize if the database is empty
            if (userRepository.count() == 0) {
                // Create admin user
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setEmail("admin@example.com");
                admin.setRoles(Set.of("ROLE_ADMIN", "ROLE_USER"));
                admin.setActive(true);
                userRepository.save(admin);

                // Create regular user
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user"));
                user.setEmail("user@example.com");
                user.setRoles(Collections.singleton("ROLE_USER"));
                user.setActive(true);
                userRepository.save(user);

                // Create some departments
                Department hrDepartment = new Department();
                hrDepartment.setName("Human Resources");
                hrDepartment.setDescription("Responsible for recruiting, screening, interviewing and placing workers");
                departmentRepository.save(hrDepartment);

                Department itDepartment = new Department();
                itDepartment.setName("Information Technology");
                itDepartment.setDescription("Responsible for the infrastructure of technical operations");
                departmentRepository.save(itDepartment);

                Department marketingDepartment = new Department();
                marketingDepartment.setName("Marketing");
                marketingDepartment.setDescription("Responsible for promoting the business and mission of the organization");
                departmentRepository.save(marketingDepartment);

                // Create some employees
                Employee employee1 = new Employee();
                employee1.setFirstName("John");
                employee1.setLastName("Doe");
                employee1.setEmail("john.doe@example.com");
                employee1.setPhone("+1234567890");
                employee1.setHireDate(LocalDate.of(2020, 1, 15));
                employee1.setSalary(new BigDecimal("75000.00"));
                employee1.setAddress("123 Main St, Anytown, USA");
                employee1.setDepartment(hrDepartment);
                employeeRepository.save(employee1);

                Employee employee2 = new Employee();
                employee2.setFirstName("Jane");
                employee2.setLastName("Smith");
                employee2.setEmail("jane.smith@example.com");
                employee2.setPhone("+1987654321");
                employee2.setHireDate(LocalDate.of(2019, 3, 20));
                employee2.setSalary(new BigDecimal("85000.00"));
                employee2.setAddress("456 Elm St, Anytown, USA");
                employee2.setDepartment(itDepartment);
                employeeRepository.save(employee2);

                Employee employee3 = new Employee();
                employee3.setFirstName("Robert");
                employee3.setLastName("Johnson");
                employee3.setEmail("robert.johnson@example.com");
                employee3.setPhone("+1122334455");
                employee3.setHireDate(LocalDate.of(2021, 5, 10));
                employee3.setSalary(new BigDecimal("65000.00"));
                employee3.setAddress("789 Oak St, Anytown, USA");
                employee3.setDepartment(marketingDepartment);
                employeeRepository.save(employee3);

                System.out.println("Sample data initialized successfully.");
            } else {
                System.out.println("Database already contains data, skipping initialization.");
            }
        };
    }
} 