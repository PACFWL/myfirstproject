package com.myfirstproject.myfirstproject;

import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.repository.MusicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
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
                                .releaseDate(LocalDate.of(2021, 5, 20))
                                .genre(Arrays.asList("Pop", "Dance"))
                                .featuredArtists(Arrays.asList("Featured Artist A", "Cantor A"))
                                .isExplicit(false)
                                .duration(3.5)
                                .rating(8.2)
                                .lyrics("Sample lyrics for Song 1...")
                                .price(new BigDecimal("1.99"))
                                .albumCover("https://static.wikia.nocookie.net/linkinpark/images/8/8d/Hybrid-Theory.jpg/revision/latest?cb=20180725161404")
                                .createdAt(Instant.now())
                                .audioQuality(Music.AudioQuality.HIGH)
                                .build(),

                        Music.builder()
                                .title("Song 2")
                                .artist("Artist 2")
                                .album("Album 2")
                                .releaseYear(2022)
                                .releaseDate(LocalDate.of(2022, 7, 15))
                                .genre(Arrays.asList("Rock", "Alternative"))
                                .featuredArtists(Arrays.asList("Featured Artist B", "Featured Artist C"))
                                .isExplicit(true)
                                .duration(4.2)
                                .rating(9.0)
                                .lyrics("Sample lyrics for Song 2...")
                                .price(new BigDecimal("2.49"))
                                .albumCover("https://static.wikia.nocookie.net/linkinpark/images/a/a1/M2M.jpg/revision/latest?cb=20180725161555")
                                .createdAt(Instant.now())
                                .audioQuality(Music.AudioQuality.LOSSLESS)
                                .build(),

                        Music.builder()
                                .title("Song 3")
                                .artist("Artist 3")
                                .album("Album 3")
                                .releaseYear(2020)
                                .releaseDate(LocalDate.of(2020, 3, 10))
                                .genre(Arrays.asList("Jazz", "Blues"))
                                .featuredArtists(Arrays.asList("Featured Artist D"))
                                .isExplicit(false)
                                .duration(5.0)
                                .rating(7.5)
                                .lyrics("Sample lyrics for Song 3...")
                                .price(new BigDecimal("3.99"))
                                .albumCover("https://static.wikia.nocookie.net/linkinpark/images/b/bf/Meteora.jpg/revision/latest?cb=20180725161334")
                                .createdAt(Instant.now())
                                .audioQuality(Music.AudioQuality.MEDIUM)
                                .build()
                );

                musicRepository.saveAll(initialMusicList);
            }
        };
    }
}
