package com.myfirstproject.myfirstproject.controller.music;

import com.myfirstproject.myfirstproject.service.music.MusicImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/music/image")
public class MusicImageController {

    private final MusicImageService musicImageService;

    public MusicImageController(MusicImageService musicImageService) {
        this.musicImageService = musicImageService;
    }

    @GetMapping("/{id}/album-cover")
    public ResponseEntity<byte[]> getAlbumCover(@PathVariable String id) {
        byte[] albumCoverImage = musicImageService.getAlbumCoverImage(id);

        if (albumCoverImage == null || albumCoverImage.length == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(albumCoverImage);
    }
}
