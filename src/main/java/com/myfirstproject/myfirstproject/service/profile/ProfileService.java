package com.myfirstproject.myfirstproject.service.profile;

import com.myfirstproject.myfirstproject.dto.profile.ProfileCreateDTO;
import com.myfirstproject.myfirstproject.dto.profile.ProfileDTO;
import com.myfirstproject.myfirstproject.dto.profile.ProfileUpdateDTO;
import com.myfirstproject.myfirstproject.mapper.ProfileMapper;
import com.myfirstproject.myfirstproject.model.Profile;
import com.myfirstproject.myfirstproject.repository.ProfileRepository;
import com.myfirstproject.myfirstproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;

    public ProfileDTO createProfile(ProfileCreateDTO dto) {
     
    if (!userRepository.existsById(dto.getUserId())) {
                throw new IllegalArgumentException("Usuário não encontrado!");
    }
        Profile profile = profileMapper.toEntity(dto);
        profile.setUserId(dto.getUserId());
        profile.setCreatedAt(Instant.now());
        profile.setUpdatedAt(Instant.now());
        profile.setStatus(true);

        Profile savedProfile = profileRepository.save(profile);
        return profileMapper.toDTO(savedProfile);
    }

    // Buscar um perfil pelo ID
    public ProfileDTO getProfileById(String id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        return profileMapper.toDTO(profile);
    }

    // Buscar um perfil pelo userId
    public ProfileDTO getProfileByUserId(String userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado para o usuário"));
        return profileMapper.toDTO(profile);
    }

    // Listar todos os perfis ativos
    public List<ProfileDTO> getAllActiveProfiles() {
        List<Profile> activeProfiles = profileRepository.findByStatus(true);
        return profileMapper.toDTOList(activeProfiles);
    }

    // Atualizar um perfil
    public ProfileDTO updateProfile(String id, ProfileUpdateDTO dto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        profileMapper.updateEntity(profile, dto);
        profile.setUpdatedAt(Instant.now());

        Profile updatedProfile = profileRepository.save(profile);
        return profileMapper.toDTO(updatedProfile);
    }

    // Deletar (soft delete) um perfil
    public void deleteProfile(String id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        profile.setStatus(false); 
        profile.setUpdatedAt(Instant.now());
        profileRepository.save(profile);
    }
}
