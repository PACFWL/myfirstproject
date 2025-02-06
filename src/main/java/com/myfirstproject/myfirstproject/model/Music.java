package com.myfirstproject.myfirstproject.model;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

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

    private LocalDate releaseDate;
    private List<String> genre;
    private List<String> featuredArtists;
    private boolean isExplicit;
    private double duration;
    private double rating;
    private String lyrics;
    private BigDecimal price;
    @Lob
    private byte[] albumCoverImage;
    private Instant createdAt; 
    private AudioQuality audioQuality;
    public enum AudioQuality {
        UNKNOWN, LOW, MEDIUM, HIGH, LOSSLESS
    }    
    //private boolean isPremium; // Nova flag de status
    //private Set<String> tags; // Conjunto de etiquetas únicas, ex: "Pop", "Clássico"
    //private LocalTime duration;
    //private Map<String, String> metadata; // Informações adicionais, como "produção", "gravação"
}
