package com.myfirstproject.myfirstproject.dto.music;

import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import com.myfirstproject.myfirstproject.model.Music.AudioQuality;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicDTO {
    private String title;
    private String artist;
    private String album;
    private int releaseYear;
    private LocalDate releaseDate;
    private List<String> genre;
    private List<String> featuredArtists;
    private boolean isExplicit;
    private double duration;
    private double rating;
    private String lyrics;
    private BigDecimal price;
    private String albumCoverImage;
    private Instant createdAt;
    private AudioQuality audioQuality;
}
