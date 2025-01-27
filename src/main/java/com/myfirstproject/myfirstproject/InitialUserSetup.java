package com.myfirstproject.myfirstproject;

import com.myfirstproject.myfirstproject.model.User;
import com.myfirstproject.myfirstproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialUserSetup {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("admin@example.com").isEmpty()) {
                userRepository.save(new User("admin@example.com", "admin123"));
                userRepository.save(new User("teste@gmail.com", "Qwerty"));

            }
        };
    }
}



