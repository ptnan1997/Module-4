package com.example.demo.controller;

import com.example.demo.dto.ProductResponse;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestParam String name,
                                        @RequestParam Double price,
                                        @RequestParam MultipartFile image) {
        ProductResponse productResponse = productService.createProduct(name.trim(), price, image);
        return ResponseEntity.ok(productResponse);
    }
}
