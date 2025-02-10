package com.myfirstproject.myfirstproject.controller.music;

import com.myfirstproject.myfirstproject.dto.music.MusicDTO;
import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.service.music.MusicFilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/music/filter")
public class MusicFilterController {

    private final MusicFilterService musicFilterService;

    public MusicFilterController(MusicFilterService musicFilterService) {
        this.musicFilterService = musicFilterService;
    }

    @GetMapping("/by-genres")
    public ResponseEntity<List<MusicDTO>> getMusicByGenres(@RequestParam List<String> genres) {
        return ResponseEntity.ok(musicFilterService.getMusicByGenre(genres));
    }

    @GetMapping("/by-artist")
    public ResponseEntity<List<MusicDTO>> getMusicByArtist(@RequestParam String artist) {
        return ResponseEntity.ok(musicFilterService.getMusicByArtist(artist));
    }

    @GetMapping("/by-rating")
    public ResponseEntity<List<MusicDTO>> getMusicByRating(@RequestParam double rating) {
        return ResponseEntity.ok(musicFilterService.getMusicByRating(rating));
    }

    @GetMapping("/by-price")
    public ResponseEntity<List<MusicDTO>> getMusicByPrice(@RequestParam BigDecimal maxPrice) {
        return ResponseEntity.ok(musicFilterService.getMusicByPrice(maxPrice));
    }
    
    @GetMapping("/by-audio-quality")
    public ResponseEntity<List<MusicDTO>> getMusicByAudioQuality(@RequestParam Music.AudioQuality audioQuality) {
        return ResponseEntity.ok(musicFilterService.getMusicByAudioQuality(audioQuality));
    }
    
    @GetMapping("/by-created-after")
    public ResponseEntity<List<MusicDTO>> getMusicByCreationDate(@RequestParam Instant createdAfter) {
        return ResponseEntity.ok(musicFilterService.getMusicByCreationDate(createdAfter));
    }
}
