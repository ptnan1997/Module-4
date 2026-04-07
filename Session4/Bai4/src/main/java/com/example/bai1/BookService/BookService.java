package com.example.bai1.BookService;


import com.example.bai1.DTO.BookRequest;
import com.example.bai1.Entity.Author;
import com.example.bai1.Entity.Book;
import com.example.bai1.InterfaceRepository.AuthorRepository;
import com.example.bai1.InterfaceRepository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public  Book getBookById(int id){
        return bookRepository.findById(id).orElse(null);
    }
    public ResponseEntity<?> addBook(BookRequest request){
        Author author = authorRepository.findById(request.getAuthor_id())
                .orElse(null);
        if (author == null){
            return new ResponseEntity<>("Tác giả không tồn tại",HttpStatus.BAD_REQUEST);
        } else{
            Book book = new Book();
            book.setTitle(request.getTitle());
            book.setPrice(request.getPrice());
            book.setAuthor(author);
            return new  ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
        }
    }
    public Book updateBook(Book book){
        return bookRepository.save(book);
    }
    public void deleteBookById(int id){
        bookRepository.deleteById(id);
    }
}
