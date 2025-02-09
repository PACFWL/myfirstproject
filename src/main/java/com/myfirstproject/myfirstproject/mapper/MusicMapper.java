package com.myfirstproject.myfirstproject.mapper;

import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import com.myfirstproject.myfirstproject.dto.music.MusicCreateDTO;
import com.myfirstproject.myfirstproject.dto.music.MusicDTO;
import com.myfirstproject.myfirstproject.dto.music.MusicUpdateDTO;
import com.myfirstproject.myfirstproject.model.Music;

public class MusicMapper {

    public static MusicDTO toDTO(Music music) {
        String albumCoverBase64 = (music.getAlbumCoverImage() != null) 
        ? Base64.getEncoder().encodeToString(music.getAlbumCoverImage()) 
        : null;//Image
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
                .albumCoverImage(albumCoverBase64)//Image
                .createdAt(music.getCreatedAt())
                .audioQuality(music.getAudioQuality())
                .tags(music.getTags())
                .metadata(music.getMetadata())
                .lastModifiedAt(music.getLastModifiedAt()) 
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
                .albumCoverImage(dto.getAlbumCoverImage())//Image
                .audioQuality(dto.getAudioQuality())
                .tags(dto.getTags())
                .metadata(dto.getMetadata())
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
        if (dto.getAlbumCoverImage() != null) music.setAlbumCoverImage(dto.getAlbumCoverImage());//Image
        if (dto.getAudioQuality() != null) music.setAudioQuality(dto.getAudioQuality());
        if (dto.getTags() != null) music.setTags(dto.getTags());
        if (dto.getMetadata() != null) music.setMetadata(dto.getMetadata());
        music.setLastModifiedAt(Instant.now()); 
    }
    
    public static List<MusicDTO> toDTOList(List<Music> musicList) {
        return musicList.stream().map(MusicMapper::toDTO).collect(Collectors.toList());
    }

}




