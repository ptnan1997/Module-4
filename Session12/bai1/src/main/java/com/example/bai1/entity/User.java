package com.example.bai1.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (unique = true)
    private String phone;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false,unique= true)
    private String email;


    private String role;
}
