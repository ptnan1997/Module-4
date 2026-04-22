package com.example.demo.controller;

import com.example.demo.DTO.EmployeeCreateDTO;
import com.example.demo.Entity.Employee;
import com.example.demo.Exception.ApiResponse;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployees();
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> save(@RequestBody EmployeeCreateDTO employeeCreateDTO) {
            Employee  employee = employeeService.create(employeeCreateDTO);

            ApiResponse<Employee> response =ApiResponse.<Employee>builder()
                    .status("SUCCESS")
                    .message("Tạo nhân viên thành công")
                    .data(employee).build();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping ("/{id}/uploads")
    public ResponseEntity<ApiResponse<Employee>> save(@PathVariable long id, @RequestParam ("file") MultipartFile file) {
            Employee emp = employeeService.updateAvatar(id, file);
            ApiResponse<Employee> response = ApiResponse.<Employee>builder()
                    .status("UPLOAD SUCCESS")
                    .message("Cập nhật file thành công")
                    .data(emp).build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
