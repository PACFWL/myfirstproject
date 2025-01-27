package com.myfirstproject.myfirstproject;

import com.myfirstproject.myfirstproject.model.User;
import com.myfirstproject.myfirstproject.repository.UserRepository;
import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.repository.MusicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialUserSetup {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, MusicRepository musicRepository) {
        return args -> {
            if (userRepository.findByEmail("admin@example.com").isEmpty()) {
                userRepository.save(new User("admin@example.com", "admin123"));
            }

            if (musicRepository.findAll().isEmpty()) {
                musicRepository.save(new Music("Song 1", "Artist 1", "Album 1", 2021));
                musicRepository.save(new Music("Song 2", "Artist 2", "Album 2", 2022));
            }
        };
    }
}



