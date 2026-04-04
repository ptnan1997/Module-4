package com.example.session03.Service;

import com.example.session03.Repository.AuthorRepository;
import com.example.session03.ra.Model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Nơi xử lý nghiệp vụ trung gian
public class AuthorServer {
    @Autowired
    private AuthorRepository authorRepository;
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
