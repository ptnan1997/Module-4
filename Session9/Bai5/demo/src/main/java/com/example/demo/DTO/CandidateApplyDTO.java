package com.example.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
public class CandidateApplyDTO {
    @NotBlank
    private String name;
    @Email
    private String email;

    private MultipartFile cvUrl;
}
