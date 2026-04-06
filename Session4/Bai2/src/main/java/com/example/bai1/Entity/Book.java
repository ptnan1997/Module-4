package com.example.bai1.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name= "title",length = 200,nullable= false)
    private String title;

    @Column (name = "price", nullable = false)
    private double price;

    // Thiết lập mối quan hệ ManyToOne với Author
    @ManyToOne
    @JoinColumn (name = "author_id") // Khóa ngoại trong bảng books
    private Author author;

}
