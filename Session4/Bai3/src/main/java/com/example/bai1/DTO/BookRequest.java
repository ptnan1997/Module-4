package com.example.bai1.DTO;


import lombok.Getter;

@Getter
public class BookRequest {
    private final int author_id;
    private final String title;
    private final double price;

    public  BookRequest(int author_id, String title, double price) {
        this.author_id = author_id;
        this.title = title;
        this.price = price;
    }
    @Override
    public String toString() {
        return "BookRequest{" +
                ", author_id=" + author_id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
