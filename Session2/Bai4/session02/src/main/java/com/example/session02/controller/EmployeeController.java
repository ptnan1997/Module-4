package com.example.session02.controller;

import com.example.session02.model.entity.Employee;
import com.example.session02.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
@Autowired
   private EmployeeService employeeService;
    @GetMapping
    public List<Employee> findAll() {
        return employeeService.getAll();
    }
    @GetMapping("/{id}")
    public Employee findById(@PathVariable int id) {
        return employeeService.getById(id);
    }
    @PostMapping
    public String create(@RequestBody Employee employee) {
        employeeService.create(employee);
        return "Created";
    }
    @DeleteMapping("/{id}")
    public String delete(@RequestBody int id) {
        employeeService.delete(id);
        return "Deleted";
    }

}
