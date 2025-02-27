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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
            @RequestParam(required = false) Boolean hasAlbumCover,
            @RequestParam(required = false) Music.AudioQuality audioQuality,
            @RequestParam(required = false) Instant createdAfter,
            @RequestParam(required = false) Set<String> tags,
            @RequestParam(required = false) Map<String, String> metadata) {
        return musicSearchService.advancedSearch(
                artist, album, genres, releaseYear, minRating, afterYear,
                isExplicit, noLyrics, featuringArtist, maxPrice, hasAlbumCover, audioQuality, createdAfter,
                tags, metadata);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MusicDTO> createMusic(
            @RequestPart("music") @Valid MusicCreateDTO musicCreateDTO,
            @RequestPart(value = "albumCoverImage", required = false) MultipartFile albumCoverImage) {
        return ResponseEntity.ok(musicService.createMusic(musicCreateDTO, albumCoverImage));
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

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MusicDTO> updateMusic(
            @PathVariable String id,
            @RequestPart("music") @Valid MusicUpdateDTO musicUpdateDTO,
            @RequestPart(value = "albumCoverImage", required = false) MultipartFile albumCoverImage) {
        return ResponseEntity.ok(musicService.updateMusic(id, musicUpdateDTO, albumCoverImage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMusic(@PathVariable String id) {
        musicService.deleteMusic(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Music deleted successfully");
        return ResponseEntity.ok(response);
    }
}

