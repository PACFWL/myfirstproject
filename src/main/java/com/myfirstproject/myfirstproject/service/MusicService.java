package com.myfirstproject.myfirstproject.service;

import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    public Music createMusic(Music music) {
        return musicRepository.save(music);
    }

    public Optional<Music> getMusicById(String id) {
        return musicRepository.findById(id);
    }

    public List<Music> getAllMusic() {
        return musicRepository.findAll();
    }

    public Music updateMusic(String id, Music music) {
        music.setId(id);
        return musicRepository.save(music);
    }

    public void deleteMusic(String id) {
        musicRepository.deleteById(id);
    }
}
