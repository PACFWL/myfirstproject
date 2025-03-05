package com.myfirstproject.myfirstproject.controller.profile;

import com.myfirstproject.myfirstproject.dto.profile.ProfileCreateDTO;
import com.myfirstproject.myfirstproject.dto.profile.ProfileDTO;
import com.myfirstproject.myfirstproject.dto.profile.ProfileUpdateDTO;
import com.myfirstproject.myfirstproject.service.profile.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    // Criar um novo perfil
    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@Valid @RequestBody ProfileCreateDTO dto) {
        ProfileDTO createdProfile = profileService.createProfile(dto);
        return ResponseEntity.ok(createdProfile);
    }

    // Buscar um perfil pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable String id) {
        ProfileDTO profile = profileService.getProfileById(id);
        return ResponseEntity.ok(profile);
    }

    // Buscar um perfil pelo userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileDTO> getProfileByUserId(@PathVariable String userId) {
        ProfileDTO profile = profileService.getProfileByUserId(userId);
        return ResponseEntity.ok(profile);
    }

    // Listar todos os perfis ativos
    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAllActiveProfiles() {
        List<ProfileDTO> profiles = profileService.getAllActiveProfiles();
        return ResponseEntity.ok(profiles);
    }

    // Atualizar um perfil
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable String id, 
                                                    @Valid @RequestBody ProfileUpdateDTO dto) {
        ProfileDTO updatedProfile = profileService.updateProfile(id, dto);
        return ResponseEntity.ok(updatedProfile);
    }

    // Deletar (soft delete) um perfil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable String id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
