package com.example.demo.service;

import com.example.demo.DTO.CandidateApplyDTO;
import com.example.demo.Entity.Candidate;
import com.example.demo.repository.CandidateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Transactional
    public Candidate apply (CandidateApplyDTO dto){
        MultipartFile file =  dto.getCvUrl();

        if(file == null){
            throw new RuntimeException("CV file không được để trống");
        }
        // Kiểm tra kiểu dữ liệu file
        String fileName =  file.getOriginalFilename();
        if (fileName == null || !(fileName.toLowerCase().endsWith(".pdf"))){
            throw new RuntimeException("Chỉ chấp nhận file PDF");
        }
        // Upload
        String fileUrl = uploadToCloudinary(file);
        // Tạo CV
        Candidate candidate = new Candidate ();
        candidate.setName(dto.getName());
        candidate.setEmail(dto.getEmail());
        candidate.setCvUrl(fileUrl);
        return  candidateRepository.save(candidate);
    }
    private String uploadToCloudinary(MultipartFile file) {
        boolean isTestFail = false;
        if (!isTestFail){
            return "https://cloudinary.com/fake-url.pdf";
        }else{
            throw new RuntimeException("Upload file thất bại (giả lập)");
        }
    }
}
