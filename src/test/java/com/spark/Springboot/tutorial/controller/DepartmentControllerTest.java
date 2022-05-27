package com.spark.Springboot.tutorial.controller;

import com.spark.Springboot.tutorial.entity.Department;
import com.spark.Springboot.tutorial.error.DepartmentNotFoundException;
import com.spark.Springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentCode("EE-06")
                .departmentName("EE")
                .departmentAddress("Chicago")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\": \"IT\",\n" +
                        "    \"departmentAddress\": \"Sunnyvale\",\n" +
                        "    \"departmentCode\": \"IT-06\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(department);

        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentId(1L)
                .departmentCode("EE-06")
                .departmentName("EE")
                .departmentAddress("Chicago")
                .build();
    }
}