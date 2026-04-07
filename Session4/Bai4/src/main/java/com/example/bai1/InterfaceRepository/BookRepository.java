package com.example.bai1.InterfaceRepository;

import com.example.bai1.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {

}
