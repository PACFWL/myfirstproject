package com.myfirstproject.myfirstproject.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "music")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Music {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @NotBlank(message = "O título é obrigatório.")
    private String title;

    @NotBlank(message = "O artista é obrigatório.")
    private String artist;

    @NotBlank(message = "O álbum é obrigatório.")
    private String album;

    @Min(value = 1800, message = "O ano de lançamento deve ser maior que 1800.")
    private int releaseYear;

    @NotBlank(message = "O gênero é obrigatório.")
    private String genre;

    private double duration;
    private double rating;
    private String lyrics;
}
