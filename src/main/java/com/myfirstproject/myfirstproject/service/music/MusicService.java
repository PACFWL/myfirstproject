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

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Validated
public class MusicService {

    private static final Logger logger = LoggerFactory.getLogger(MusicService.class);
    private final MusicRepository musicRepository;
    private final MusicSearchService musicSearchService;
    private final MusicImageService musicImageService;

    public MusicService(MusicRepository musicRepository, MusicSearchService musicSearchService, MusicImageService musicImageService) {
        this.musicRepository = musicRepository;
        this.musicSearchService = musicSearchService;
        this.musicImageService = musicImageService;
    }
    //@Cacheable(value = "musicSearch", key = "#artist + #album + #genres + #releaseYear + #minRating + #afterYear")
    public List<MusicDTO> advancedSearch(String artist, String album, List<String> genres, Integer releaseYear,
                                          Double minRating, Integer afterYear, Boolean isExplicit,
                                          Boolean noLyrics, String featuringArtist, BigDecimal maxPrice, 
                                          Music.AudioQuality audioQuality, Instant createdAfter) {
        return musicSearchService.advancedSearch(artist, album, genres, releaseYear, minRating, afterYear, isExplicit, noLyrics, featuringArtist, maxPrice, audioQuality, createdAfter);
    }
    


    //@CacheEvict(value = {"musicSearch", "musicSearchPaged"}, allEntries = true)
    public MusicDTO createMusic(MusicCreateDTO musicCreateDTO, MultipartFile albumCoverImage) {
        logger.info("Criando música: {}", musicCreateDTO);
        Music music = MusicMapper.toEntity(musicCreateDTO);

        music.setAlbumCoverImage(musicImageService.processAlbumCover(albumCoverImage));

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

    //@CacheEvict(value = {"musicSearch", "musicSearchPaged"}, allEntries = true)
    public MusicDTO updateMusic(String id, MusicUpdateDTO musicUpdateDTO, MultipartFile albumCoverImage) {
        logger.info("Atualizando música com ID: {}", id);
        Music existingMusic = musicRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Música não encontrada"));

        MusicMapper.updateEntityFromDTO(existingMusic, musicUpdateDTO);

        existingMusic.setAlbumCoverImage(musicImageService.processAlbumCover(albumCoverImage));

        existingMusic.setLastModifiedAt(Instant.now());
        return MusicMapper.toDTO(musicRepository.save(existingMusic));
    }

    //@CacheEvict(value = {"musicSearch", "musicSearchPaged"}, allEntries = true)
    public void deleteMusic(String id) {
        logger.info("Deletando música com ID: {}", id);
        if (!musicRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Música não encontrada");
        }
        musicRepository.deleteById(id);
    }
    //@Cacheable(value = "musicSearchPaged", key = "#page + '-' + #size")
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
    //@Cacheable(value = "musicSearchPaged", key = "#artist + '-' + #album + '-' + #genres + '-' + #releaseYear + '-' + #minRating + '-' + #afterYear + '-' + #isExplicit + '-' + #noLyrics + '-' + #featuringArtist + '-' + #maxPrice + '-' + #audioQuality + '-' + #createdAfter + '-' + #tags + '-' + #metadata + '-' + #page + '-' + #size")
    public MusicPageDTO advancedSearchPaged(String artist, String album, List<String> genres, Integer releaseYear,
        Double minRating, Integer afterYear, Boolean isExplicit,
        Boolean noLyrics, String featuringArtist, BigDecimal maxPrice, Boolean hasAlbumCover,
        Music.AudioQuality audioQuality, Instant createdAfter, Set<String> tags, Map<String, String> metadata, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MusicDTO> musicPage = musicSearchService.advancedSearchPaged(artist, album, genres, releaseYear, minRating, afterYear, isExplicit, noLyrics, featuringArtist, maxPrice, hasAlbumCover, audioQuality, createdAfter, tags, metadata, pageable);

        return new MusicPageDTO(
        musicPage.getContent(),
        musicPage.getNumber(),
        musicPage.getSize(),
        musicPage.getTotalElements(),
        musicPage.getTotalPages(),
        musicPage.isLast()
        );
    }

}
