package com.example.session02.service;

import com.example.session02.model.entity.Employee;
import com.example.session02.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(int id) {
        return employeeRepository.findById(id);
    }

    public void create(Employee employee) {
        employeeRepository.create(employee);
    }

    public void delete(int id) {
        employeeRepository.delete(id);
    }
}
