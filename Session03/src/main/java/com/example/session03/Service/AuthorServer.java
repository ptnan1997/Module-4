package com.example.session03.Service;

import com.example.session03.Repository.AuthorRepository;
import com.example.session03.ra.Model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service // Nơi xử lý nghiệp vụ trung gian
public class AuthorServer {
    @Autowired
    private AuthorRepository authorRepository;
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    public Author  saveAuthor(Author author) {
        authorRepository.save(author);
        return author;
    }
    public Author getAuthorById(int id) {
        return authorRepository.findById(id);
    }
    public Author updateAuthor(int id,Author author) {
        return authorRepository.updateById(id, author);
    }
    public boolean deleteAuthor(int id) {
        Author author = authorRepository.findById(id);
        String nameRex = "Admin";
        if ( author != null) {
            if(author.getName().equalsIgnoreCase(nameRex)){
                return false;
            }
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Author> searchAuthors(String keyword){
        List<Author> result = new ArrayList<>();
        authorRepository.findAll().forEach(author -> {
            if(author.getName().toLowerCase().contains(keyword.toLowerCase())){
                result.add(author);
            }
        });
                return result;
    }
}
