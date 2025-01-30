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
                musicRepository.save(Music.builder()
                        .title("Song 1")
                        .artist("Artist 1")
                        .album("Album 1")
                        .releaseYear(2021)
                        .genre("Pop")
                        .duration(3.5)
                        .rating(8.2)
                        .lyrics("Sample lyrics for Song 1...")
                        .build());

                musicRepository.save(Music.builder()
                        .title("Song 2")
                        .artist("Artist 2")
                        .album("Album 2")
                        .releaseYear(2022)
                        .genre("Rock")
                        .duration(4.2)
                        .rating(9.0)
                        .lyrics("Sample lyrics for Song 2...")
                        .build());
            }
        };
    }
}
