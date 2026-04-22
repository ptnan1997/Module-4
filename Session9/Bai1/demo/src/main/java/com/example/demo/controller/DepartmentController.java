package com.example.demo.controller;

import com.example.demo.DTO.DepartmentDTO;
import com.example.demo.Entity.Department;
import com.example.demo.Entity.Employee;
import com.example.demo.Exception.ApiResponse;
import com.example.demo.repository.DepartmentRepos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    @Autowired
    private DepartmentRepos departmentRepos;
    @GetMapping
    public List<Department> getAllDepartments() {
       return departmentRepos.findAll();
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Department>> save(@Valid @RequestBody DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());
        Department saved = departmentRepos.save(department);
        ApiResponse<Department> response = ApiResponse.<Department>builder()
                .status("SUCCESS")
                .message("Tạo phòng ban thành công")
                .data(saved).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
