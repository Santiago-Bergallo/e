package com.example.eCommerce.v2.security;

import jakarta.servlet.FilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class WebSecurityConfig  {

    @Autowired
    JWTFilterRequest jwtFilterRequest;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable();
        http.addFilterBefore(jwtFilterRequest, AuthorizationFilter.class);
        http.authorizeHttpRequests()
                .requestMatchers("/products", "/users", "/login", "/me", "/register", "/verify").permitAll()
                .anyRequest().permitAll();
        return http.build();
    }
}
