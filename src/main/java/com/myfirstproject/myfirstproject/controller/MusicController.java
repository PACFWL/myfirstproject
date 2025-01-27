package com.myfirstproject.myfirstproject.controller;

import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.service.MusicService;
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
    public Music createMusic(@RequestBody Music music) {
        return musicService.createMusic(music);
    }

    @GetMapping("/{id}")
    public Optional<Music> getMusicById(@PathVariable String id) {
        return musicService.getMusicById(id);
    }

    @GetMapping
    public List<Music> getAllMusic() {
        return musicService.getAllMusic();
    }

    @PutMapping("/{id}")
    public Music updateMusic(@PathVariable String id, @RequestBody Music music) {
        return musicService.updateMusic(id, music);
    }

    @DeleteMapping("/{id}")
    public void deleteMusic(@PathVariable String id) {
        musicService.deleteMusic(id);
    }
}
