package com.myfirstproject.myfirstproject.dto;

public class MusicDTO {

    private String title;
    private String artist;
    private String album;
    private int releaseYear;

    public MusicDTO() {}

    public MusicDTO(String title, String artist, String album, int releaseYear) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseYear = releaseYear;
    }

    // Getters e Setters
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
