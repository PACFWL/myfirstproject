package com.myfirstproject.myfirstproject.model;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Document(collection = "artist")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artist {
    
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String name;
    private String nacionality;
}
