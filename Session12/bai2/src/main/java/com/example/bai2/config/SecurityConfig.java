package com.example.bai2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/products")
                        .hasAnyRole("ADMIN", "STAFF", "CUSTOMER")

                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/products")
                        .hasAnyRole("ADMIN", "STAFF")

                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/products/**")
                        .hasAnyRole("ADMIN", "STAFF")

                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/products/**")
                        .hasAnyRole("ADMIN", "STAFF")

                        .anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
