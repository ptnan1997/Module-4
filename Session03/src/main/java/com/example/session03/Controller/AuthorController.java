package com.example.session03.Controller;


import com.example.session03.Service.AuthorServer;
import com.example.session03.ra.Model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    @Autowired
    private AuthorServer  authorServer;
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors(){
        return new  ResponseEntity<>(authorServer.getAllAuthors(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody Author author){
        Author newAuthor = authorServer.saveAuthor(author);
        return new  ResponseEntity<>(newAuthor, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findAuthorById(@PathVariable int id){
        Author foundAuthor = authorServer.getAuthorById(id);
        if (foundAuthor != null){
            return new  ResponseEntity<>(foundAuthor,HttpStatus.OK);
        }
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable int id, @RequestBody Author author){
        Author updatedAuthor = authorServer.updateAuthor(id, author);
        if (updatedAuthor != null){
            return new  ResponseEntity<>(updatedAuthor,HttpStatus.OK);
        }
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable int id){
        boolean deleted = authorServer.deleteAuthor(id);
        if (deleted){
            return new  ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        return new  ResponseEntity<>("Can not deleted",HttpStatus.NOT_FOUND);
    }
    @GetMapping ("/search")
    public ResponseEntity<List<Author>> filterAuthorByName(@RequestParam("name") String keyword){
        List<Author> rs = authorServer.searchAuthors(keyword);
        if (rs.isEmpty()){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new  ResponseEntity<>(rs, HttpStatus.OK);
    }
}
