package com.myfirstproject.myfirstproject;

import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.repository.MusicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InitialMusicSetup {

    @Bean
    CommandLineRunner initMusicDatabase(MusicRepository musicRepository) {
        return args -> {
            if (musicRepository.findAll().isEmpty()) {
                List<Music> initialMusicList = Arrays.asList(
                        Music.builder()
                                .title("Song 1")
                                .artist("Artist 1")
                                .album("Album 1")
                                .releaseYear(2021)
                                .genre(Arrays.asList("Pop", "Dance")) // Agora aceita múltiplos gêneros
                                .duration(3.5)
                                .rating(8.2)
                                .lyrics("Sample lyrics for Song 1...")
                                .build(),

                        Music.builder()
                                .title("Song 2")
                                .artist("Artist 2")
                                .album("Album 2")
                                .releaseYear(2022)
                                .genre(Arrays.asList("Rock", "Alternative")) // Lista de gêneros
                                .duration(4.2)
                                .rating(9.0)
                                .lyrics("Sample lyrics for Song 2...")
                                .build(),

                        Music.builder()
                                .title("Song 3")
                                .artist("Artist 3")
                                .album("Album 3")
                                .releaseYear(2020)
                                .genre(Arrays.asList("Jazz", "Blues")) // Exemplo de outros gêneros
                                .duration(5.0)
                                .rating(7.5)
                                .lyrics("Sample lyrics for Song 3...")
                                .build()
                );

                musicRepository.saveAll(initialMusicList);
            }
        };
    }
}
