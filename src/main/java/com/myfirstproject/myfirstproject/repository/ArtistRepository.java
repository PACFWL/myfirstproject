package com.myfirstproject.myfirstproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myfirstproject.myfirstproject.model.Artist;

public interface ArtistRepository extends MongoRepository<Artist, String>{}
