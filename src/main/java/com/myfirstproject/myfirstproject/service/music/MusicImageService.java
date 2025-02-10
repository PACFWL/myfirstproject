package com.myfirstproject.myfirstproject.service.music;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.repository.MusicRepository;

import java.io.IOException;

@Service
public class MusicImageService {

    private static final Logger logger = LoggerFactory.getLogger(MusicImageService.class);
    private final MusicRepository musicRepository;

    public MusicImageService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public byte[] processAlbumCover(MultipartFile albumCoverImage) {
        if (albumCoverImage == null || albumCoverImage.isEmpty()) {
            return null;
        }

        try {
            logger.info("Processando imagem da capa do álbum.");
            return albumCoverImage.getBytes();
        } catch (IOException e) {
            logger.error("Erro ao processar a imagem da capa do álbum.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao processar a imagem");
        }
    }

    public byte[] getAlbumCoverImage(String id) {
        logger.info("Buscando imagem da capa do álbum para a música com ID: {}", id);
        
        Music music = musicRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada"));

        if (music.getAlbumCoverImage() == null || music.getAlbumCoverImage().length == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagem da capa do álbum não encontrada");
        }

        return music.getAlbumCoverImage();
    }
}
