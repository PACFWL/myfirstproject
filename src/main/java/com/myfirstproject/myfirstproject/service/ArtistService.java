package com.myfirstproject.myfirstproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myfirstproject.myfirstproject.model.Artist;
import com.myfirstproject.myfirstproject.repository.ArtistRepository;

@Service
public class ArtistService {
    
    @Autowired 
    private ArtistRepository artistRepository;

    public Artist createArtist(Artist artist){
        return artistRepository.save(artist);
    }
    
    public Optional<Artist> getArtistById(String id){
        return artistRepository.findById(id);
    }
    
    public List<Artist> getAllArtist() {
        return artistRepository.findAll();
    }

    public Artist updateArtist(String id, Artist artist) {
        artist.setId(id);
        return artistRepository.save(artist);
    }

    public void deleteArtist(String id) {
        artistRepository.deleteById(id);
    }
}

