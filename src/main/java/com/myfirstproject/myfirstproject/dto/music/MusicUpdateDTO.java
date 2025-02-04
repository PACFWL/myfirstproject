package com.myfirstproject.myfirstproject.dto.music;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.myfirstproject.myfirstproject.model.Music.AudioQuality;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicUpdateDTO {
    private String title;
    private String artist;
    private String album;
    private Integer releaseYear;
    private LocalDate releaseDate;
    private List<String> genre;
    private List<String> featuredArtists;
    private Boolean isExplicit;
    private Double duration;
    private Double rating;
    private String lyrics;
    private BigDecimal price;
    private String albumCover;
    private AudioQuality audioQuality;
}
