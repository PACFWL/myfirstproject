package com.myfirstproject.myfirstproject.controller;

import com.myfirstproject.myfirstproject.dto.MusicCreateDTO;
import com.myfirstproject.myfirstproject.dto.MusicDTO;
import com.myfirstproject.myfirstproject.dto.MusicUpdateDTO;
import com.myfirstproject.myfirstproject.service.MusicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    private final MusicService musicService;

    
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
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

    @GetMapping("/by-genres")
    public ResponseEntity<List<MusicDTO>> getMusicByGenres(@RequestParam List<String> genres) {
        return ResponseEntity.ok(musicService.getMusicByGenre(genres));
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
}