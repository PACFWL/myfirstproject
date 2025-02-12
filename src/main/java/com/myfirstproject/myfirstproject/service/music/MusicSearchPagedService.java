package com.myfirstproject.myfirstproject.service.music;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myfirstproject.myfirstproject.dto.music.MusicDTO;
import com.myfirstproject.myfirstproject.dto.music.MusicPageDTO;
import com.myfirstproject.myfirstproject.dto.music.MusicSearchDTO;


@Service
public class MusicSearchPagedService {

    private final MusicSearchService musicSearchService;

        public MusicSearchPagedService(MusicSearchService musicSearchService){
            this.musicSearchService = musicSearchService;
        }

    public MusicPageDTO advancedSearchPaged(MusicSearchDTO searchDTO, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MusicDTO> musicPage = musicSearchService.advancedSearchPaged(
            searchDTO.getArtist(),
            searchDTO.getAlbum(),
            searchDTO.getGenres(),
            searchDTO.getReleaseYear(),
            searchDTO.getMinRating(),
            searchDTO.getAfterYear(),
            searchDTO.getIsExplicit(),
            searchDTO.getNoLyrics(),
            searchDTO.getFeaturingArtist(),
            searchDTO.getMaxPrice(),
            searchDTO.getHasAlbumCover(),
            searchDTO.getAudioQuality(),
            searchDTO.getCreatedAfter(),
            searchDTO.getTags(),
            searchDTO.getMetadata(),
            pageable
    );

        return new MusicPageDTO(
                musicPage.getContent(),
                musicPage.getNumber(),
                musicPage.getSize(),
                musicPage.getTotalElements(),
                musicPage.getTotalPages(),
                musicPage.isLast()
        );
    }
    
}
