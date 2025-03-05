package com.myfirstproject.myfirstproject.model;

import lombok.*;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @NotBlank(message = "O userId é obrigatório.")
    private String userId; // Relacionamento com User

    @NotBlank(message = "O nome de exibição é obrigatório.")
    @Size(max = 100, message = "O nome de exibição pode ter no máximo 100 caracteres.")
    private String displayName;

    @Size(max = 500, message = "A bio pode ter no máximo 500 caracteres.")
    private String bio;

    private String profilePictureUrl;

    private String socialMediaLink;

    @Size(max = 100, message = "A localização pode ter no máximo 100 caracteres.")
    private String location;

    @Pattern(regexp = "^(https?:\\/\\/)?([\\w.-]+)\\.([a-z]{2,})(\\/\\S*)?$", 
             message = "URL do site inválida.")
    private String website;

    @Past(message = "A data de nascimento deve estar no passado.")
    private LocalDateTime dateOfBirth;

    private Instant createdAt;  // Alterado para Instant
    private Instant updatedAt;  // Alterado para Instant

    @Builder.Default
    private boolean status = true; 
    
}
