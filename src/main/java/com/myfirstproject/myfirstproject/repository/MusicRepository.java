package com.myfirstproject.myfirstproject.repository;

import com.myfirstproject.myfirstproject.model.Music;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends MongoRepository<Music, String> {}
