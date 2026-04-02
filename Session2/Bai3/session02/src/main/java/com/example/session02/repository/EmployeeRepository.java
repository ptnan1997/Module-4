package com.example.session02.repository;

import com.example.session02.model.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();
    public EmployeeRepository (){
        employees.add(new Employee(1,"Nguyễn Văn A","a@gmail.com","IT"));
        employees.add(new Employee(2,"Trần Thị B","b@gmail.com","HR"));
    }
    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void create(Employee employee) {
        employees.add(employee);
    }

    public void delete(int id) {
        employees.removeIf(e -> e.getId() == id);
    }
}
