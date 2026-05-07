package com.example.bai1.service;

import com.example.bai1.model.dto.request.ProductCreateDTO;
import com.example.bai1.model.entity.Product;
import com.example.bai1.repository.ProductRepository;
import com.example.bai1.security.UserDetailServiceCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserDetailServiceCustom userDetailService;

    public Product add(ProductCreateDTO productCreateDTO) {
        Product product = new Product();
        product.setProductName(productCreateDTO.getProductName());
        product.setPrice(productCreateDTO.getPrice());
        product.setQuantity(productCreateDTO.getQuantity());
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
