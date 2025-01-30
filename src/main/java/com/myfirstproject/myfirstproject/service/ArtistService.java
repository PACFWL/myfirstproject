package com.myfirstproject.myfirstproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.myfirstproject.myfirstproject.dto.ArtistDTO;
import com.myfirstproject.myfirstproject.model.Artist;
import com.myfirstproject.myfirstproject.repository.ArtistRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class ArtistService {
    
    private static final Logger logger = LoggerFactory.getLogger(ArtistService.class);

    @Autowired 
    private ArtistRepository artistRepository;

    public Artist createArtist(Artist artist){
        logger.info("Criando artista: {}", artist);
        return artistRepository.save(artist);
    }
    
    public Optional<ArtistDTO> getArtistById(String id){
        logger.info("Buscando artista com ID: {}", id);
        return artistRepository.findById(id)
            .map(artist -> new ArtistDTO(artist.getName(), artist.getNacionality()));
    }
    
    public List<ArtistDTO> getAllArtist() {
        logger.info("Buscando todas as artistas.");
        return artistRepository.findAll().stream()
            .map(artist -> new ArtistDTO(artist.getName(), artist.getNacionality()))
            .collect(Collectors.toList());
    }

    public Artist updateArtist(String id, Artist artist) {
        logger.info("Atualizando artista com ID: {}", id);
        artist.setId(id);
        return artistRepository.save(artist);
    }

    public void deleteArtist(String id) {
        logger.info("Deletando artista com ID: {}", id);
        artistRepository.deleteById(id);
    }
}

