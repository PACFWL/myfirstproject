package com.myfirstproject.myfirstproject.dto;

public class ArtistDTO {
    
    private String name;
    private String nacionality;

    public ArtistDTO() {}

    public ArtistDTO(String name, String nacionality){

        this.name = name;
        this.nacionality = nacionality;

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getNacionality(){
        return nacionality;
    }
    
    public void setNacionality(String nacionality){
        this.nacionality = nacionality;
    }
}
