package com.example.leave_management.config;

import com.example.leave_management.entity.User;
import com.example.leave_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create admin user if not exists
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@company.com");
            admin.setFullName("System Administrator");
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
        }

        // Create sample employee if not exists
        if (!userRepository.existsByUsername("employee")) {
            User employee = new User();
            employee.setUsername("employee");
            employee.setPassword(passwordEncoder.encode("employee123"));
            employee.setEmail("employee@company.com");
            employee.setFullName("John Employee");
            employee.setRole(User.Role.EMPLOYEE);
            userRepository.save(employee);
        }
    }
} 