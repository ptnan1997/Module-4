package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

@Service
public class FileStorageService implements IFileStorageService {
    private final Path rootLocation = Paths.get("uploads");

    private final List<String> ALLOWED_EXTENSIONS = Arrays.asList("png", "jpg", "jpeg");
    @Override
    public void intit() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Không thể khởi tạo thư mục lưu trữ!", e);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Không thể lưu file rỗng.");
            }

            String originalFilename = file.getOriginalFilename();

            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
            }

            if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
                throw new IllegalArgumentException("Chỉ chấp nhận ảnh (png, jpg, jpeg)");
            }

            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(originalFilename))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return originalFilename;

        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu file.", e);
        }
    }
}
