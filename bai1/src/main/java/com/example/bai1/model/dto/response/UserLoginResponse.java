package com.example.bai1.model.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {
    private String  username;
    private String type;
    private String access_token;
}
