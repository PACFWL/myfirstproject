package com.myfirstproject.myfirstproject.dto.artist;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistDTO {
    
    private String name;
    private String nacionality;
}
