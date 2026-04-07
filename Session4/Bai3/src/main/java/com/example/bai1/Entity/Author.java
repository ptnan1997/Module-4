package com.example.bai1.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @Column(name = "full_name",length = 100, nullable = false)
    private String fullName;
    @Getter
    @Setter
    @Column(name = "email",length = 100, nullable = false)
    private String email;
    // Thiết lập mối quan hệ OneToMany với Books
    @OneToMany (mappedBy = "author",cascade = CascadeType.ALL)// cascade dùng để khi xóa tác giả thì sẽ xóa luôn các quan hệ một nhiều liên quan đến author đó
    private List<Book> books;
    public Author (int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }
    public Author() {}
    
}
