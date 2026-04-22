package com.example.demo.controller;

import com.example.demo.DTO.CandidateApplyDTO;
import com.example.demo.Entity.Candidate;
import com.example.demo.Exception.ApiResponse;
import com.example.demo.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<Candidate>> applyCandidate(@ModelAttribute CandidateApplyDTO candidateApplyDTO) {
            Candidate  candidate = candidateService.apply( candidateApplyDTO );

            ApiResponse<Candidate> response = ApiResponse.<Candidate>builder()
                    .status("SUCCESS")
                    .message("Dăng ký thành công")
                    .data(candidate ).build();
            return ResponseEntity.ok(response);
    }

}
