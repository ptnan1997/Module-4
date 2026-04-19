package com.example.demo.service;

import com.example.demo.dto.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IProductService {
    ProductResponse createProduct(String name, Double price, MultipartFile image);

}
