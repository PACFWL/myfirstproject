package com.myfirstproject.myfirstproject.service;

import com.myfirstproject.myfirstproject.dto.MusicDTO;
import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.repository.MusicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class MusicService {

    private static final Logger logger = LoggerFactory.getLogger(MusicService.class);

    @Autowired
    private MusicRepository musicRepository;

    public Music createMusic(Music music) {
        logger.info("Criando música: {}", music);
        return musicRepository.save(music);
    }

    public Optional<MusicDTO> getMusicById(String id) {
        logger.info("Buscando música com ID: {}", id);
        return musicRepository.findById(id)
                .map(music -> convertToDTO(music));
    }

    public List<MusicDTO> getAllMusic() {
        logger.info("Buscando todas as músicas.");
        return musicRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MusicDTO> getMusicByGenre(String genre) {
        logger.info("Buscando músicas do gênero: {}", genre);
        return musicRepository.findByGenre(genre).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MusicDTO> getMusicByArtist(String artist) {
        logger.info("Buscando músicas do artista: {}", artist);
        return musicRepository.findByArtist(artist).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MusicDTO> getMusicByRating(double rating) {
        logger.info("Buscando músicas com avaliação maior ou igual a: {}", rating);
        return musicRepository.findByRatingGreaterThanEqual(rating).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Music updateMusic(String id, Music music) {
        logger.info("Atualizando música com ID: {}", id);
        music.setId(id);
        return musicRepository.save(music);
    }

    public void deleteMusic(String id) {
        logger.info("Deletando música com ID: {}", id);
        musicRepository.deleteById(id);
    }

    private MusicDTO convertToDTO(Music music) {
        return MusicDTO.builder()
                .title(music.getTitle())
                .artist(music.getArtist())
                .album(music.getAlbum())
                .releaseYear(music.getReleaseYear())
                .genre(music.getGenre())
                .duration(music.getDuration())
                .rating(music.getRating())
                .lyrics(music.getLyrics())
                .build();
    }
}
