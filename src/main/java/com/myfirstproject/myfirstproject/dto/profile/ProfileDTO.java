package com.myfirstproject.myfirstproject.dto.profile;
import lombok.*;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDTO {
    private String id;
    private String userId;
    private String displayName;
    private String bio;
    private String profilePictureUrl;
    private String socialMediaLink;
    private String location;
    private String website;
    private Instant dateOfBirth;  
    private Instant createdAt;   
    private Instant updatedAt;   
    private boolean status;
}
