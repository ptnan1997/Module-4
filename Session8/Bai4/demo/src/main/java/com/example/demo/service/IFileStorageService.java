package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;


public interface IFileStorageService {
    void intit();
    String storeFile (MultipartFile file);
}
