package com.example.bai1.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Getter
    @Setter
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column (name= "title",length = 200,nullable= false)
    private String title;

    @Getter
    @Setter
    @Column (name = "price", nullable = false)
    private double price;

    // Thiết lập mối quan hệ ManyToOne với Author
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn (name = "author_id") // Khóa ngoại trong bảng books
    private Author author;
    public  Book() {
    }
    public Book(int id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
    @ManyToMany
    @JoinTable (
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
}
