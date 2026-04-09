package com.example.bai1.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "categoryName", length = 100, nullable = false)
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private List<Book> books;
}
