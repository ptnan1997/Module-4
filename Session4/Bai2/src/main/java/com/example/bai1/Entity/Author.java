package com.example.bai1.Entity;

import jakarta.persistence.*;

import java.util.List;

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
    // Thiết lập mối quan hệ OneToMany với Books
    @OneToMany (mappedBy = "author",cascade = CascadeType.ALL)// cascade dùng để khi xóa tác giả thì sẽ xóa luôn các quan hệ một nhiều liên quan đến author đó
    private List<Book> books;
}
