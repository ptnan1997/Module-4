package com.example.bai1.model.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterResponse {
    private String username;
    private String fullName;
    private String email;
    private String role;
}
