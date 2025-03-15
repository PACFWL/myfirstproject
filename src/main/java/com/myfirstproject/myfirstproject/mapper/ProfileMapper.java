package com.myfirstproject.myfirstproject.mapper;

import com.myfirstproject.myfirstproject.dto.profile.ProfileCreateDTO;
import com.myfirstproject.myfirstproject.dto.profile.ProfileDTO;
import com.myfirstproject.myfirstproject.dto.profile.ProfileUpdateDTO;
import com.myfirstproject.myfirstproject.model.Profile;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfileMapper {

    public ProfileDTO toDTO(Profile profile) {
        if (profile == null) {
            return null;
        }
        return ProfileDTO.builder()
                .id(profile.getId())
                .userId(profile.getUserId())
                .displayName(profile.getDisplayName())
                .bio(profile.getBio())
                .profilePictureUrl(profile.getProfilePictureUrl())
                .socialMediaLink(profile.getSocialMediaLink())
                .location(profile.getLocation())
                .website(profile.getWebsite())
                .dateOfBirth(profile.getDateOfBirth() != null ? profile.getDateOfBirth().toInstant(java.time.ZoneOffset.UTC) : null) 
                .createdAt(profile.getCreatedAt())
                .updatedAt(profile.getUpdatedAt())
                .status(profile.isStatus())
                .build();
    }

    public Profile toEntity(ProfileCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        return Profile.builder()
                .userId(dto.getUserId())
                .displayName(dto.getDisplayName())
                .bio(dto.getBio())
                .profilePictureUrl(dto.getProfilePictureUrl())
                .socialMediaLink(dto.getSocialMediaLink())
                .location(dto.getLocation())
                .website(dto.getWebsite())
                .dateOfBirth(dto.getDateOfBirth())
                .build();
    }

    public void updateEntity(Profile profile, ProfileUpdateDTO dto) {
        if (dto == null || profile == null) {
            return;
        }
        if (dto.getDisplayName() != null) profile.setDisplayName(dto.getDisplayName());
        if (dto.getBio() != null) profile.setBio(dto.getBio());
        if (dto.getProfilePictureUrl() != null) profile.setProfilePictureUrl(dto.getProfilePictureUrl());
        if (dto.getSocialMediaLink() != null) profile.setSocialMediaLink(dto.getSocialMediaLink());
        if (dto.getLocation() != null) profile.setLocation(dto.getLocation());
        if (dto.getWebsite() != null) profile.setWebsite(dto.getWebsite());
        if (dto.getDateOfBirth() != null) profile.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getStatus() != null) profile.setStatus(dto.getStatus());
    }

    public List<ProfileDTO> toDTOList(List<Profile> profiles) {
        return profiles.stream().map(this::toDTO).collect(Collectors.toList());
    }
}