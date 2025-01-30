package com.myfirstproject.myfirstproject.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicDTO {
    private String title;
    private String artist;
    private String album;
    private int releaseYear;
    private String genre;
    private double duration;
    private double rating;
    private String lyrics;
}
