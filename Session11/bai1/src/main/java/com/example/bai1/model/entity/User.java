package com.example.bai1.model.entity;

import com.example.bai1.constant.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String username;
    private String password;
    private String fullName;
    private String email;
    @Builder.Default
    private boolean enabled = false; // Mặc định là chưa kích hoạt

    @Column(name = "otp_code")
    private String otpCode;

    @Column(name = "otp_expiration")
    private LocalDateTime otpExpiration;

    @Enumerated(EnumType.STRING)
    private Role role;
}
