package com.example.session03.Repository;

import com.example.session03.ra.Model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository // Tầng lưu trữ nơi tương tác với dữ liệu thao tác với dữ liệu gốc
public class AuthorRepository {
    private final List<Author> authors = new ArrayList<>();
    public AuthorRepository(){
        authors.add( new Author(1, "Nguyễn Thị Nguyên", "nguyen1998@gmail.com"));
        authors.add( new Author(2, "Trương Thị Hồng Gấm", "Gam123@gamil.com"));
        authors.add( new Author(3, "Võ Tấn Đạt", "votandat2002@gmail.com"));
    }
    public List<Author> findAll(){
        return authors;
    }
}
