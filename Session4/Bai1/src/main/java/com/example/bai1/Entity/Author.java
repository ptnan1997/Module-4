package com.example.bai1.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "full_name",length = 100, nullable = false)
    private String fullName;
    @Column(name = "email",length = 100, nullable = false)
    private String email;
}
