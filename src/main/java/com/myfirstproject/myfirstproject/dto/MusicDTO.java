package com.myfirstproject.myfirstproject.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicDTO {
    private String title;
    private String artist;
    private String album;
    private int releaseYear;
    private List<String> genre; 
    private double duration;
    private double rating;
    private String lyrics;
}
