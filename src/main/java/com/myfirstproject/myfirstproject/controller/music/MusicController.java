package com.myfirstproject.myfirstproject.controller.music;

import com.myfirstproject.myfirstproject.dto.music.MusicCreateDTO;
import com.myfirstproject.myfirstproject.dto.music.MusicDTO;
import com.myfirstproject.myfirstproject.dto.music.MusicUpdateDTO;
import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.service.music.MusicSearchService;
import com.myfirstproject.myfirstproject.service.music.MusicService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    private final MusicService musicService;
    private final MusicSearchService musicSearchService;

    public MusicController(MusicService musicService, MusicSearchService musicSearchService) {
        this.musicService = musicService;
        this.musicSearchService = musicSearchService;
    }

    @GetMapping("/search")
    public List<MusicDTO> advancedSearch(
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String album,
            @RequestParam(required = false) List<String> genres,
            @RequestParam(required = false) Integer releaseYear,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Integer afterYear,
            @RequestParam(required = false) Boolean isExplicit,
            @RequestParam(required = false) Boolean noLyrics,
            @RequestParam(required = false) String featuringArtist,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Music.AudioQuality audioQuality,
            @RequestParam(required = false) Instant createdAfter) {
        return musicSearchService.advancedSearch(
                artist, album, genres, releaseYear, minRating, afterYear,
                isExplicit, noLyrics, featuringArtist, maxPrice, audioQuality, createdAfter
        );
    }

    
    @PostMapping
    public ResponseEntity<MusicDTO> createMusic(@Valid @RequestBody MusicCreateDTO musicCreateDTO) {
        return ResponseEntity.ok(musicService.createMusic(musicCreateDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicDTO> getMusicById(@PathVariable String id) {
        return musicService.getMusicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MusicDTO>> getAllMusic() {
        return ResponseEntity.ok(musicService.getAllMusic());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicDTO> updateMusic(@PathVariable String id, @Valid @RequestBody MusicUpdateDTO musicUpdateDTO) {
        return ResponseEntity.ok(musicService.updateMusic(id, musicUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable String id) {
        musicService.deleteMusic(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-genres")
    public ResponseEntity<List<MusicDTO>> getMusicByGenres(@RequestParam List<String> genres) {
        return ResponseEntity.ok(musicService.getMusicByGenre(genres));
    }

    @GetMapping("/by-artist")
    public ResponseEntity<List<MusicDTO>> getMusicByArtist(@RequestParam String artist) {
    return ResponseEntity.ok(musicService.getMusicByArtist(artist));
    }

    @GetMapping("/by-rating")
    public ResponseEntity<List<MusicDTO>> getMusicByRating(@RequestParam double rating) {
        return ResponseEntity.ok(musicService.getMusicByRating(rating));
    }

    @GetMapping("/by-price")
    public ResponseEntity<List<MusicDTO>> getMusicByPrice(@RequestParam BigDecimal maxPrice) {
        return ResponseEntity.ok(musicService.getMusicByPrice(maxPrice));
    }
    
    @GetMapping("/by-audio-quality")
    public ResponseEntity<List<MusicDTO>> getMusicByAudioQuality(@RequestParam Music.AudioQuality audioQuality) {
        return ResponseEntity.ok(musicService.getMusicByAudioQuality(audioQuality));
    }
    
    @GetMapping("/by-created-after")
    public ResponseEntity<List<MusicDTO>> getMusicByCreationDate(@RequestParam Instant createdAfter) {
        return ResponseEntity.ok(musicService.getMusicByCreationDate(createdAfter));
    }
    

}