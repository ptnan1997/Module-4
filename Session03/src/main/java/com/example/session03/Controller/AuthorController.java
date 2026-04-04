package com.example.session03.Controller;


import com.example.session03.Service.AuthorServer;
import com.example.session03.ra.Model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
