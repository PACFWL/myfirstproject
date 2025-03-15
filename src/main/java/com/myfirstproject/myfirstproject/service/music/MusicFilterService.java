package com.myfirstproject.myfirstproject.service.music;

import com.myfirstproject.myfirstproject.dto.music.MusicDTO;
import com.myfirstproject.myfirstproject.mapper.MusicMapper;
import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.repository.MusicRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MusicFilterService {

    private static final Logger logger = LoggerFactory.getLogger(MusicFilterService.class);
    private final MusicRepository musicRepository;

    public MusicFilterService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<MusicDTO> getMusicByGenre(List<String> genres) {
        logger.info("Buscando músicas dos gêneros: {}", genres);
        return musicRepository.findByGenreIn(genres).stream()
                .map(MusicMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<MusicDTO> getMusicByArtist(String artist) {
        logger.info("Buscando músicas do artista: {}", artist);
        return musicRepository.findByArtist(artist).stream()
                .map(MusicMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<MusicDTO> getMusicByRating(double rating) {
        logger.info("Buscando músicas com avaliação maior ou igual a: {}", rating);
        return musicRepository.findByRatingGreaterThanEqual(rating).stream()
                .map(MusicMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<MusicDTO> getMusicByPrice(BigDecimal maxPrice) {
    logger.info("Buscando músicas com preço até: {}", maxPrice);
    return musicRepository.findByPriceLessThanEqual(maxPrice).stream()
            .map(MusicMapper::toDTO)
            .collect(Collectors.toList());
    }

    public List<MusicDTO> getMusicByAudioQuality(Music.AudioQuality audioQuality) {
        logger.info("Buscando músicas com qualidade de áudio: {}", audioQuality);
        return musicRepository.findByAudioQuality(audioQuality).stream()
                .map(MusicMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<MusicDTO> getMusicByCreationDate(Instant createdAfter) {
        logger.info("Buscando músicas criadas após: {}", createdAfter);
        return musicRepository.findByCreatedAtAfter(createdAfter).stream()
                .map(MusicMapper::toDTO)
                .collect(Collectors.toList());
    }

        public List<MusicDTO> getMusicByTags(Set<String> tags) {
        logger.info("Buscando músicas com as tags: {}", tags);
        return musicRepository.findByTagsIn(tags).stream()
                .map(MusicMapper::toDTO)
                .collect(Collectors.toList());
    }
}
