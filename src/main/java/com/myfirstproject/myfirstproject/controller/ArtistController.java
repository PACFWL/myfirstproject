package com.myfirstproject.myfirstproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.myfirstproject.myfirstproject.dto.ArtistDTO;
import com.myfirstproject.myfirstproject.model.Artist;
import com.myfirstproject.myfirstproject.service.ArtistService;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {
    
    @Autowired
    private ArtistService artistService;
    
    @PostMapping
    public Artist createArtist(@RequestBody Artist artist){
        return artistService.createArtist(artist);
    }

    @GetMapping("/{id}")
    public Optional<ArtistDTO> getArtistById(@PathVariable String id) {
        return artistService.getArtistById(id);
    }

    @GetMapping
    public List<ArtistDTO> getAllArtist() {
        return artistService.getAllArtist();
    }

    @PutMapping("{id}")
    public Artist updateArtist(@PathVariable String id, @RequestBody Artist artist){
        return artistService.updateArtist(id, artist);        
    }

    @DeleteMapping("{id}")
    public void deleteArtist(@PathVariable String id){
        artistService.deleteArtist(id);
    }
}
