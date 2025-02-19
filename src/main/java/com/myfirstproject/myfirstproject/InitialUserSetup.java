package com.myfirstproject.myfirstproject;

import com.myfirstproject.myfirstproject.model.User;
import com.myfirstproject.myfirstproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.UUID;

@Configuration
public class InitialUserSetup {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("abcdef@example.com").isEmpty()) {
                User admin = User.builder()
                        .id(UUID.randomUUID().toString())
                        .email("abcdef@example.com")
                        .password(passwordEncoder.encode("admin123"))  
                        .role(User.Role.ADMIN)
                        .createdAt(Instant.now())
                        .lastModifiedAt(Instant.now())
                        .build();

                User user = User.builder()
                        .id(UUID.randomUUID().toString())
                        .email("teste@gmail.com")
                        .password(passwordEncoder.encode("Qwerty")) 
                        .role(User.Role.USER)
                        .createdAt(Instant.now())
                        .lastModifiedAt(Instant.now())
                        .build();

                userRepository.save(admin);
                userRepository.save(user);
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }
}