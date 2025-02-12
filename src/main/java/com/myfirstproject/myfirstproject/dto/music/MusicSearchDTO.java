package com.myfirstproject.myfirstproject.dto.music;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.myfirstproject.myfirstproject.model.Music;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicSearchDTO {
    private String artist;
    private String album;
    private List<String> genres;
    private Integer releaseYear;
    private Double minRating;
    private Integer afterYear;
    private Boolean isExplicit;
    private Boolean noLyrics;
    private String featuringArtist;
    private BigDecimal maxPrice;
    private Boolean hasAlbumCover;
    private Music.AudioQuality audioQuality;
    private Instant createdAfter;
    private Set<String> tags;
    private Map<String, String> metadata;
}
