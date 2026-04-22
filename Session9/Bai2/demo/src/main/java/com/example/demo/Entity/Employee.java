package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    private Long id;
    @Column (nullable=false)
    private String  fullName;
    @Column (nullable=false,unique = true)
    private String  email;

    private String phone;

    private Double salary;

    private String avatarUrl;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
