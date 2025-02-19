package com.myfirstproject.myfirstproject.repository;

import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.model.Music.AudioQuality;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;


@Repository
public interface MusicRepository extends MongoRepository<Music, String> { 
   
    List<Music> findByReleaseYear(Integer releaseYear);
    List<Music> findByArtist(String artist);
    List<Music> findByAlbum(String album);

    List<Music> findByReleaseDate(LocalDate releaseDate);//Metodo Experimental

    List<Music> findByGenreIn(List<String> genres);
    List<Music> findByRatingGreaterThanEqual(Double rating);
    List<Music> findByReleaseYearGreaterThan(Integer year);
    List<Music> findByIsExplicitTrue();
    List<Music> findByLyricsIsNull();

    List<Music> findByAlbumIn(List<String> albuns);//Metodo Experimento
    List<Music> findByArtistIn(List<String> artists);//Metodo Experimento
    List<Music> findByDurationGreaterThanEqual(Double rating);//Metodo Experimental
    
    List<Music> findByArtistAndFeaturedArtistsContaining(String artist, String featuredArtist); 

    List<Music> findByPriceLessThanEqual(BigDecimal price);
    List<Music> findByPriceGreaterThan(BigDecimal price);
    List<Music> findByAudioQuality(AudioQuality audioQuality);
    List<Music> findByCreatedAtAfter(Instant createdAt);
    List<Music> findByAlbumCoverImageIsNotNull();
    List<Music> findByAlbumCoverImageIsNull();
    Page<Music> findByAlbumCoverImageIsNotNull(Pageable pageable);
    Page<Music> findByAlbumCoverImageIsNull(Pageable pageable);
    Page<Music> findByReleaseYear(Integer releaseYear, Pageable pageable);
    Page<Music> findByRatingGreaterThanEqual(Double rating, Pageable pageable);
    Page<Music> findByDurationGreaterThanEqual(Double duration, Pageable pageable);
    List<Music> findByTagsIn(Set<String> tags);
}
