package com.myfirstproject.myfirstproject.service;

import com.myfirstproject.myfirstproject.dto.MusicCreateDTO;
import com.myfirstproject.myfirstproject.dto.MusicDTO;
import com.myfirstproject.myfirstproject.dto.MusicUpdateDTO;
import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.repository.MusicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Validated
public class MusicService {

   private static final Logger logger = LoggerFactory.getLogger(MusicService.class);
    private final MusicRepository musicRepository;
    private final MongoTemplate mongoTemplate;

    public MusicService(MusicRepository musicRepository, MongoTemplate mongoTemplate) {
        this.musicRepository = musicRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<MusicDTO> advancedSearch(String artist, String album, List<String> genres, Integer releaseYear,
                                          Double minRating, Integer afterYear, Boolean isExplicit,
                                          Boolean noLyrics, String featuringArtist) {
        Query query = new Query();

        if (artist != null) query.addCriteria(Criteria.where("artist").is(artist));
        if (album != null) query.addCriteria(Criteria.where("album").is(album));
        if (genres != null && !genres.isEmpty()) query.addCriteria(Criteria.where("genre").in(genres));
        if (releaseYear != null) query.addCriteria(Criteria.where("releaseYear").is(releaseYear));
        if (minRating != null) query.addCriteria(Criteria.where("rating").gte(minRating));
        if (afterYear != null) query.addCriteria(Criteria.where("releaseYear").gt(afterYear));
        if (isExplicit != null) query.addCriteria(Criteria.where("isExplicit").is(isExplicit));
        if (noLyrics != null && noLyrics) query.addCriteria(Criteria.where("lyrics").is(null));
        if (featuringArtist != null) query.addCriteria(Criteria.where("featuredArtists").in(featuringArtist));

        logger.info("Executando busca avançada com critérios: {}", query);
        
        List<Music> results = mongoTemplate.find(query, Music.class);
        return results.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    public MusicDTO createMusic(MusicCreateDTO musicCreateDTO) {
        logger.info("Criando música: {}", musicCreateDTO);
        Music music = convertToEntity(musicCreateDTO);
        return convertToDTO(musicRepository.save(music));
    }

    public Optional<MusicDTO> getMusicById(String id) {
        logger.info("Buscando música com ID: {}", id);
        return musicRepository.findById(id).map(this::convertToDTO);
    }

    public List<MusicDTO> getAllMusic() {
        logger.info("Buscando todas as músicas.");
        return musicRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MusicDTO> getMusicByGenre(List<String> genres) {
        logger.info("Buscando músicas dos gêneros: {}", genres);
        return musicRepository.findByGenreIn(genres).stream()
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

    public MusicDTO updateMusic(String id, MusicUpdateDTO musicUpdateDTO) {
        logger.info("Atualizando música com ID: {}", id);
        Music existingMusic = musicRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Música não encontrada"));
        updateEntityFromDTO(existingMusic, musicUpdateDTO);
        return convertToDTO(musicRepository.save(existingMusic));
    }

    public void deleteMusic(String id) {
        logger.info("Deletando música com ID: {}", id);
        if (!musicRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Música não encontrada");
        }
        musicRepository.deleteById(id);
    }

    private MusicDTO convertToDTO(Music music) {
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
                .build();
    }
    

    private Music convertToEntity(MusicCreateDTO dto) {
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
                .build();
    }
    

    private void updateEntityFromDTO(Music music, MusicUpdateDTO dto) {
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
    }
      
}


