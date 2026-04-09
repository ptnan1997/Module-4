package com.example.bai1.BookController;

import com.example.bai1.Service.BookService;
import com.example.bai1.DTO.BookRequest;
import com.example.bai1.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookRequest request){
        return new  ResponseEntity<>(bookService.addBook(request), HttpStatus.CREATED);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> getBooksById(@PathVariable int id){
        Book book = bookService.getBookById(id);
        if (book == null){
            return new ResponseEntity<>("Mã sách không tồn tại",HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
    }

}
