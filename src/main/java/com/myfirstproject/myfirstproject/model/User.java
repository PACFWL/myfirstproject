package com.myfirstproject.myfirstproject.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    public enum Role {
        ADMIN, USER
    }

    private Instant createdAt;
    
    @LastModifiedDate
    private Instant lastModifiedAt;
}
