package com.myfirstproject.myfirstproject.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "music")
public class Music {

    @Id
    private String id;

    @NotBlank(message = "O título é obrigatório.")
    private String title;

    @NotBlank(message = "O artista é obrigatório.")
    private String artist;

    @NotBlank(message = "O álbum é obrigatório.")
    private String album;

    @Min(value = 1800, message = "O ano de lançamento deve ser maior que 1800.")
    private int releaseYear;

    public Music() {}

    public Music(String title, String artist, String album, int releaseYear) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseYear = releaseYear;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}

