package com.myfirstproject.myfirstproject.service.music;

import com.myfirstproject.myfirstproject.dto.music.MusicDTO;
import com.myfirstproject.myfirstproject.dto.music.MusicPageDTO;
import com.myfirstproject.myfirstproject.mapper.MusicMapper;
import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.repository.MusicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicPagedService {

    private final MusicRepository musicRepository;

    public MusicPagedService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    
    }

    public MusicPageDTO getAllMusicPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Music> musicPage = musicRepository.findAll(pageable);

        List<MusicDTO> musicDTOList = musicPage.getContent().stream()
                .map(MusicMapper::toDTO)
                .collect(Collectors.toList());

        return new MusicPageDTO(
                musicDTOList,
                musicPage.getNumber(),
                musicPage.getSize(),
                musicPage.getTotalElements(),
                musicPage.getTotalPages(),
                musicPage.isLast()
        );
    }
}
