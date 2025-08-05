package com.example.leave_management.service;

import com.example.leave_management.dto.AuthRequest;
import com.example.leave_management.entity.User;
import com.example.leave_management.repository.UserRepository;
import com.example.leave_management.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    private AuthRequest authRequest;
    private User user;

    @BeforeEach
    void setUp() {
        authRequest = new AuthRequest("testuser", "password123");
        
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("encodedPassword");
        user.setEmail("testuser@company.com");
        user.setFullName("Test User");
        user.setRole(User.Role.EMPLOYEE);
    }

    @Test
    void register_ShouldCreateNewUser() {
        // Given
        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtUtil.generateToken(any(User.class))).thenReturn("jwt-token");

        // When
        var result = authService.register(authRequest);

        // Then
        assertNotNull(result);
        assertEquals("jwt-token", result.getToken());
        assertEquals("testuser", result.getUsername());
        assertEquals("EMPLOYEE", result.getRole());
        
        verify(userRepository).existsByUsername("testuser");
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));
        verify(jwtUtil).generateToken(any(User.class));
    }

    @Test
    void register_ShouldThrowException_WhenUsernameExists() {
        // Given
        when(userRepository.existsByUsername("testuser")).thenReturn(true);

        // When & Then
        assertThrows(RuntimeException.class, () -> authService.register(authRequest));
        verify(userRepository).existsByUsername("testuser");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void login_ShouldReturnAuthResponse() {
        // Given
        when(userRepository.findByUsername("testuser")).thenReturn(java.util.Optional.of(user));
        when(jwtUtil.generateToken(any(User.class))).thenReturn("jwt-token");

        // When
        var result = authService.login(authRequest);

        // Then
        assertNotNull(result);
        assertEquals("jwt-token", result.getToken());
        assertEquals("testuser", result.getUsername());
        assertEquals("EMPLOYEE", result.getRole());
        
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByUsername("testuser");
        verify(jwtUtil).generateToken(any(User.class));
    }
} 