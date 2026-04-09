package com.example.bai1.Entity;

import jakarta.persistence.*;

import lombok.Getter;

import lombok.Setter;

import java.time.LocalDate;

@Entity

@Table(name = "borrowTickets")
public class BorrowTicket {
    @Getter
    @Setter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @Column (name = "studentName",length = 100, nullable = false)
    private String studentName;
    @Getter
    @Setter
    @Column (name = "borrowDate",length = 100,nullable = false)
    private LocalDate borrowDate;
    @Getter
    @Setter
    @Column (name = "status",length = 100,nullable = false)
    private String status ;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    public BorrowTicket() {
    }
    public BorrowTicket(int id, String studentName, LocalDate borrowDate, String status, Book book) {
        this.id = id;
        this.studentName = studentName;
        this.borrowDate = borrowDate;
        this.status = status;
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
