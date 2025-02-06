package com.myfirstproject.myfirstproject.dto.music;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myfirstproject.myfirstproject.model.Music.AudioQuality;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicCreateDTO {
    @NotBlank(message = "O título é obrigatório.")
    private String title;

    @NotBlank(message = "O artista é obrigatório.")
    private String artist;

    @NotBlank(message = "O álbum é obrigatório.")
    private String album;

    @Min(value = 1800, message = "O ano de lançamento deve ser maior que 1800.")
    private int releaseYear;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    
    private List<String> genre;
    private List<String> featuredArtists;

    @JsonProperty("isExplicit")
    private boolean isExplicit;

    private double duration;
    private double rating;
    private String lyrics;
    private BigDecimal price;
    private byte[] albumCoverImage;
    private AudioQuality audioQuality;
}
