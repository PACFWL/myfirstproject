package com.myfirstproject.myfirstproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myfirstproject.myfirstproject.model.Artist;

@Repository
public interface ArtistRepository extends MongoRepository<Artist, String>{}
