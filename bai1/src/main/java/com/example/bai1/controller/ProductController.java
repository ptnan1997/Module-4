package com.example.bai1.controller;

import com.example.bai1.model.entity.Product;
import com.example.bai1.model.dto.request.ProductCreateDTO;
import com.example.bai1.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ap/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return  ResponseEntity.ok().body(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> save(@Valid @RequestBody ProductCreateDTO productCreateDTO) {
        return new ResponseEntity<>(productService.add(productCreateDTO), HttpStatus.CREATED);
    }
}
