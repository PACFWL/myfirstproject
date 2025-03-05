package com.myfirstproject.myfirstproject.repository;

import com.myfirstproject.myfirstproject.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
    Optional<Profile> findByUserId(String userId); 
    List<Profile> findByStatus(boolean status);
}
