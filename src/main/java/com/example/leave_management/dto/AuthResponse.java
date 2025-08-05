package com.example.leave_management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private Long expiresIn; // in seconds
    
    // User information
    private String username;
    private String email;
    private String fullName;
    private String role;
    
    // Response metadata
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private boolean success = true;
    
    // Constructor for backward compatibility
    public AuthResponse(String token, String username, String role, String fullName) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.fullName = fullName;
        this.timestamp = LocalDateTime.now();
        this.message = "Authentication successful";
    }
} 