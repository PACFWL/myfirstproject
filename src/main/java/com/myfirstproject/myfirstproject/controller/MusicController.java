package com.myfirstproject.myfirstproject.controller;

import com.myfirstproject.myfirstproject.dto.MusicDTO;
import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.service.MusicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping
    public Music createMusic(@Valid @RequestBody Music music) {
        return musicService.createMusic(music);
    }

    @GetMapping("/{id}")
    public Optional<MusicDTO> getMusicById(@PathVariable String id) {
        return musicService.getMusicById(id);
    }

    @GetMapping
    public List<MusicDTO> getAllMusic() {
        return musicService.getAllMusic();
    }

    @GetMapping("/by-genres")
    public List<MusicDTO> getMusicByGenres(@RequestParam List<String> genres) {
        return musicService.getMusicByGenre(genres);
    }

    @PutMapping("/{id}")
    public Music updateMusic(@PathVariable String id, @Valid @RequestBody Music music) {
        return musicService.updateMusic(id, music);
    }

    @DeleteMapping("/{id}")
    public void deleteMusic(@PathVariable String id) {
        musicService.deleteMusic(id);
    }
}
