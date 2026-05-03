package com.example.bai1.controller;

import com.example.bai1.dto.RegisterRequest;
import com.example.bai1.entity.Employee;
import com.example.bai1.entity.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private UserPrincipal userPrincipal;

    @GetMapping("/employees")
    public List<Employee> getEmployees(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        System.out.println("Username: " + userPrincipal.getUsername());

        return List.of(
                new Employee(1,"Nguyen Van A",1000),
                new Employee(2,"Tran Thi B",2000),
                new Employee(3,"Duong Thi C",3000)
        );
    }
    @PostMapping("/auth/test")
    public ResponseEntity<List<Employee>> addEmployee (@RequestBody Employee employee) {
        List<Employee>  employees = new ArrayList<>();
        employees.add(employee);
        return new  ResponseEntity<>(employees, HttpStatus.OK);
    }
}
