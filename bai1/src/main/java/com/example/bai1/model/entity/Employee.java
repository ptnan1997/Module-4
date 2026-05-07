package com.example.bai1.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String fullName;
    private double salary;
    private String email;
    private String department;
    private String avatarUrl;

    public Employee(long id ,String fullName, double salary) {
        this.fullName = fullName;
        this.id = id ;
        this.salary = salary;
    }
}