package com.myfirstproject.myfirstproject.mapper;

import java.time.Instant;

import com.myfirstproject.myfirstproject.dto.music.MusicCreateDTO;
import com.myfirstproject.myfirstproject.dto.music.MusicDTO;
import com.myfirstproject.myfirstproject.dto.music.MusicUpdateDTO;
import com.myfirstproject.myfirstproject.model.Music;

public class MusicMapper {

    public static MusicDTO toDTO(Music music) {
        return MusicDTO.builder()
                .title(music.getTitle())
                .artist(music.getArtist())
                .album(music.getAlbum())
                .releaseYear(music.getReleaseYear())
                .releaseDate(music.getReleaseDate())
                .genre(music.getGenre())
                .featuredArtists(music.getFeaturedArtists())
                .isExplicit(music.isExplicit())
                .duration(music.getDuration())
                .rating(music.getRating())
                .lyrics(music.getLyrics())
                .price(music.getPrice())
                .albumCover(music.getAlbumCover())
                .createdAt(music.getCreatedAt())
                .audioQuality(music.getAudioQuality())
                .build();
    }

    public static Music toEntity(MusicCreateDTO dto) {
        return Music.builder()
                .title(dto.getTitle())
                .artist(dto.getArtist())
                .album(dto.getAlbum())
                .releaseYear(dto.getReleaseYear())
                .releaseDate(dto.getReleaseDate())
                .genre(dto.getGenre())
                .featuredArtists(dto.getFeaturedArtists())
                .isExplicit(dto.isExplicit())
                .duration(dto.getDuration())
                .rating(dto.getRating())
                .lyrics(dto.getLyrics())
                .price(dto.getPrice())
                .albumCover(dto.getAlbumCover())
                .audioQuality(dto.getAudioQuality())
                .createdAt(Instant.now()) 
                .build();
    }

    public static void updateEntityFromDTO(Music music, MusicUpdateDTO dto) {
        if (dto.getTitle() != null) music.setTitle(dto.getTitle());
        if (dto.getArtist() != null) music.setArtist(dto.getArtist());
        if (dto.getAlbum() != null) music.setAlbum(dto.getAlbum());
        if (dto.getReleaseYear() != null) music.setReleaseYear(dto.getReleaseYear());
        if (dto.getReleaseDate() != null) music.setReleaseDate(dto.getReleaseDate());
        if (dto.getGenre() != null) music.setGenre(dto.getGenre());
        if (dto.getFeaturedArtists() != null) music.setFeaturedArtists(dto.getFeaturedArtists());
        if (dto.getIsExplicit() != null) music.setExplicit(dto.getIsExplicit());
        if (dto.getDuration() != null) music.setDuration(dto.getDuration());
        if (dto.getRating() != null) music.setRating(dto.getRating());
        if (dto.getLyrics() != null) music.setLyrics(dto.getLyrics());
        if (dto.getPrice() != null) music.setPrice(dto.getPrice());
        if (dto.getAlbumCover() != null) music.setAlbumCover(dto.getAlbumCover());
        if (dto.getAudioQuality() != null) music.setAudioQuality(dto.getAudioQuality());
    }
}




