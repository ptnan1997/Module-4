package com.example.demo.service;

import com.example.demo.dto.ProductResponse;
import com.example.demo.exception.CustomException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(String name, Double price, MultipartFile image) {
        if (name == null || name.isBlank()){
            throw new CustomException("Tên sản phẩm không được để trống");
        }
        if (image == null || image.isEmpty()){
            throw new CustomException("Ảnh sản phẩm là bắt buộc");
        }
        try {
            // tạo thư mục nếu chưa có
            String UPLOADS_DIR = "uploads/";
            File folder = new File(UPLOADS_DIR);
            if (!folder.exists()) {
                boolean created = folder.mkdir();
                if (!created) {
                    throw new RuntimeException("Không thể tạo thư mục upload");
                }
            }

            // đổi tên file
            String originalFilename = image.getOriginalFilename();
            String extension = "";
            if (originalFilename != null) {
                int index = originalFilename.lastIndexOf(".");
                if (index > 0) {
                    extension = originalFilename.substring(index);
                }
            }
            String newFileName = UUID.randomUUID() + extension;

            // lưu file
            Path filePath = Paths.get(UPLOADS_DIR + newFileName);



            // save DB
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setImage(newFileName);
            try {

                Files.write(filePath, image.getBytes());
                productRepository.save(product);
            } catch (Exception e){
                Files.deleteIfExists(filePath); // 👈 rollback file
                throw e;
            }


            return new ProductResponse(
                product.getId(), product.getName(), product.getPrice(), product.getImage()
            );

        } catch (IOException e) {
            throw new CustomException("Lỗi khi upload file");
        }

    }
}
