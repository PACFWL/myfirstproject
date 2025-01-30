package com.myfirstproject.myfirstproject.repository;

import com.myfirstproject.myfirstproject.model.Music;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends MongoRepository<Music, String> {
    List<Music> findByGenre(String genre);
    List<Music> findByArtist(String artist);
    List<Music> findByRatingGreaterThanEqual(double rating);
}
