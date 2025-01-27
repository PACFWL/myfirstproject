package com.myfirstproject.myfirstproject;

import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.repository.MusicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialMusicSetup {

    @Bean
    CommandLineRunner initMusicDatabase(MusicRepository musicRepository) {
        return args -> {
            if (musicRepository.findAll().isEmpty()) {
                musicRepository.save(new Music("Song 1", "Artist 1", "Album 1", 2021));
                musicRepository.save(new Music("Song 2", "Artist 2", "Album 2", 2022));
            }
        };
    }
}
