package com.myfirstproject.myfirstproject.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
}
