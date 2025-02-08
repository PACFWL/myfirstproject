package com.myfirstproject.myfirstproject.service.music;

import com.myfirstproject.myfirstproject.dto.music.MusicCreateDTO;
import com.myfirstproject.myfirstproject.dto.music.MusicDTO;
import com.myfirstproject.myfirstproject.dto.music.MusicPageDTO;
import com.myfirstproject.myfirstproject.dto.music.MusicUpdateDTO;
import com.myfirstproject.myfirstproject.mapper.MusicMapper;
import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.repository.MusicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Validated
public class MusicService {

    private static final Logger logger = LoggerFactory.getLogger(MusicService.class);
    private final MusicRepository musicRepository;
    private final MusicSearchService musicSearchService;

    public MusicService(MusicRepository musicRepository, MusicSearchService musicSearchService) {
        this.musicRepository = musicRepository;
        this.musicSearchService = musicSearchService;
    }

    public List<MusicDTO> advancedSearch(String artist, String album, List<String> genres, Integer releaseYear,
                                          Double minRating, Integer afterYear, Boolean isExplicit,
                                          Boolean noLyrics, String featuringArtist, BigDecimal maxPrice, 
                                          Music.AudioQuality audioQuality, Instant createdAfter) {
        return musicSearchService.advancedSearch(artist, album, genres, releaseYear, minRating, afterYear, isExplicit, noLyrics, featuringArtist, maxPrice, audioQuality, createdAfter);
    }
    
public MusicDTO createMusic(MusicCreateDTO musicCreateDTO, MultipartFile albumCoverImage) {
    logger.info("Criando música: {}", musicCreateDTO);

    Music music = MusicMapper.toEntity(musicCreateDTO);

    if (albumCoverImage != null && !albumCoverImage.isEmpty()) {
        try {
            music.setAlbumCoverImage(albumCoverImage.getBytes());
            logger.info("Imagem da capa do álbum foi armazenada.");
        } catch (IOException e) {
            logger.error("Erro ao processar a imagem da capa do álbum.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao processar a imagem");
        }
    }

    return MusicMapper.toDTO(musicRepository.save(music));
}

    public Optional<MusicDTO> getMusicById(String id) {
        logger.info("Buscando música com ID: {}", id);
        return musicRepository.findById(id).map(MusicMapper::toDTO);
    }

    public List<MusicDTO> getAllMusic() {
        logger.info("Buscando todas as músicas.");
        return musicRepository.findAll().stream()
                .map(MusicMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MusicDTO updateMusic(String id, MusicUpdateDTO musicUpdateDTO, MultipartFile albumCoverImage) {
        logger.info("Atualizando música com ID: {}", id);
        Music existingMusic = musicRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Música não encontrada"));
    
        MusicMapper.updateEntityFromDTO(existingMusic, musicUpdateDTO);
    
        if (albumCoverImage != null && !albumCoverImage.isEmpty()) {
            try {
                existingMusic.setAlbumCoverImage(albumCoverImage.getBytes());
                logger.info("Imagem da capa do álbum foi atualizada.");
            } catch (IOException e) {
                logger.error("Erro ao processar a imagem da capa do álbum.", e);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao processar a imagem");
            }
        }
    
        return MusicMapper.toDTO(musicRepository.save(existingMusic));
    }

    public void deleteMusic(String id) {
        logger.info("Deletando música com ID: {}", id);
        if (!musicRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Música não encontrada");
        }
        musicRepository.deleteById(id);
    }

    public byte[] getAlbumCoverImage(String id) {
        logger.info("Buscando imagem da capa do álbum da música com ID: {}", id);
        
        Music music = musicRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Música não encontrada"));
    
        if (music.getAlbumCoverImage() == null || music.getAlbumCoverImage().length == 0) {
            throw new ResponseStatusException(NOT_FOUND, "Imagem da capa do álbum não encontrada");
        }
    
        return music.getAlbumCoverImage();
    }//Image
    
    public MusicPageDTO getAllMusicPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Music> musicPage = musicRepository.findAll(pageable);

        List<MusicDTO> musicDTOList = musicPage.getContent().stream()
                .map(MusicMapper::toDTO)
                .collect(Collectors.toList());

        return new MusicPageDTO(
                musicDTOList,
                musicPage.getNumber(),
                musicPage.getSize(),
                musicPage.getTotalElements(),
                musicPage.getTotalPages(),
                musicPage.isLast()
        );
    }

    public MusicPageDTO advancedSearchPaged(String artist, String album, List<String> genres, Integer releaseYear,
        Double minRating, Integer afterYear, Boolean isExplicit,
        Boolean noLyrics, String featuringArtist, BigDecimal maxPrice, Boolean hasAlbumCover,
        Music.AudioQuality audioQuality, Instant createdAfter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MusicDTO> musicPage = musicSearchService.advancedSearchPaged(artist, album, genres, releaseYear, minRating, afterYear, isExplicit, noLyrics, featuringArtist, maxPrice, hasAlbumCover, audioQuality, createdAfter, pageable);

        return new MusicPageDTO(
        musicPage.getContent(),
        musicPage.getNumber(),
        musicPage.getSize(),
        musicPage.getTotalElements(),
        musicPage.getTotalPages(),
        musicPage.isLast()
        );
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


}
