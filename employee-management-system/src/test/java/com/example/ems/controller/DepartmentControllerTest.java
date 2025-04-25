package com.example.ems.controller;

import com.example.ems.dto.DepartmentDto;
import com.example.ems.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private DepartmentDto departmentDto;

    @BeforeEach
    public void setup() {
        departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("IT Department");
        departmentDto.setDescription("Information Technology Department");
        departmentDto.setEmployeeCount(5);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateDepartment() throws Exception {
        when(departmentService.createDepartment(any(DepartmentDto.class))).thenReturn(departmentDto);

        mockMvc.perform(post("/api/departments")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"IT Department\",\"description\":\"Information Technology Department\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("IT Department"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetDepartmentById() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(departmentDto);

        mockMvc.perform(get("/api/departments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("IT Department"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetAllDepartments() throws Exception {
        List<DepartmentDto> departments = Arrays.asList(
                departmentDto,
                new DepartmentDto(2L, "HR", "Human Resources", 3)
        );
        Page<DepartmentDto> departmentPage = new PageImpl<>(departments);

        when(departmentService.getAllDepartments(any(Pageable.class))).thenReturn(departmentPage);

        mockMvc.perform(get("/api/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.content", hasSize(2)))
                .andExpect(jsonPath("$.data.content[0].id").value(1))
                .andExpect(jsonPath("$.data.content[0].name").value("IT Department"))
                .andExpect(jsonPath("$.data.content[1].id").value(2))
                .andExpect(jsonPath("$.data.content[1].name").value("HR"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateDepartment() throws Exception {
        DepartmentDto updatedDepartment = new DepartmentDto();
        updatedDepartment.setId(1L);
        updatedDepartment.setName("Updated IT Department");
        updatedDepartment.setDescription("Updated Information Technology Department");
        updatedDepartment.setEmployeeCount(5);

        when(departmentService.updateDepartment(eq(1L), any(DepartmentDto.class))).thenReturn(updatedDepartment);

        mockMvc.perform(put("/api/departments/1")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated IT Department\",\"description\":\"Updated Information Technology Department\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("Updated IT Department"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteDepartment() throws Exception {
        mockMvc.perform(delete("/api/departments/1")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(get("/api/departments"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testForbiddenAccess() throws Exception {
        mockMvc.perform(post("/api/departments")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"IT Department\",\"description\":\"Information Technology Department\"}"))
                .andExpect(status().isForbidden());
    }
} 