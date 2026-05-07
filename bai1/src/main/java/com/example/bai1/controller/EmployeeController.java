package com.example.bai1.controller;

import com.example.bai1.model.dto.request.EmployeeCreateDTO;
import com.example.bai1.model.entity.Employee;
import com.example.bai1.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@EnableGlobalAuthentication()
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"Nguyễn Công Hưởng",5000));
        employees.add(new Employee(2,"Phạm Tuấn Bình",5000));
        employees.add(new Employee(3,"Nguyễn Văn A",3000));
        Date date = new Date("22/05/2025");
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @ModelAttribute EmployeeCreateDTO employeeCreateDTO){
        return new ResponseEntity<>(employeeService.addEmployee(employeeCreateDTO), HttpStatus.CREATED);
    }
}