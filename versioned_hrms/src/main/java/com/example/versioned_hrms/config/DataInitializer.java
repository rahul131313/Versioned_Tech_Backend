package com.example.versioned_hrms.config;

import com.example.versioned_hrms.entity.User;
import com.example.versioned_hrms.repositary.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedUsers(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.findByUsername("admin").isEmpty()) {

                User user = new User();

                user.setUsername("admin");
                user.setPassword(
                        passwordEncoder.encode("Admin123")
                );
                user.setRole("ADMIN");
                user.setEnabled(true);

                userRepository.save(user);
            }
        };
    }
}
