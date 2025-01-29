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
                .map(music -> new MusicDTO(music.getTitle(), music.getArtist(), music.getAlbum(), music.getReleaseYear()));
    }

    public List<MusicDTO> getAllMusic() {
        logger.info("Buscando todas as músicas.");
        return musicRepository.findAll().stream()
                .map(music -> new MusicDTO(music.getTitle(), music.getArtist(), music.getAlbum(), music.getReleaseYear()))
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
}
