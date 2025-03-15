package com.myfirstproject.myfirstproject.controller.music;

import com.myfirstproject.myfirstproject.dto.music.MusicPageDTO;
import com.myfirstproject.myfirstproject.dto.music.MusicSearchDTO;
import com.myfirstproject.myfirstproject.service.music.MusicPagedService;
import com.myfirstproject.myfirstproject.service.music.MusicSearchPagedService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/music/paged")
public class MusicPagedController {

    private final MusicPagedService musicPagedService;
    private final MusicSearchPagedService musicSearchPagedService;

    public MusicPagedController(MusicPagedService musicPagedService, MusicSearchPagedService musicSearchPagedService) {
        this.musicPagedService = musicPagedService;
        this.musicSearchPagedService = musicSearchPagedService;
    }

    @GetMapping
    public ResponseEntity<MusicPageDTO> getAllMusicPaged(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(musicPagedService.getAllMusicPaged(page, size));
    }

    @GetMapping("/search")
    public MusicPageDTO searchPaged(@ModelAttribute MusicSearchDTO searchDTO, 
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        return musicSearchPagedService.advancedSearchPaged(searchDTO, page, size);
    }
}