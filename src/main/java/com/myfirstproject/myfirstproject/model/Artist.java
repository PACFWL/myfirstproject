package com.myfirstproject.myfirstproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "artist")
public class Artist {
    
    @Id
    private String id;
    private String name;
    private String nacionality;

    public Artist() {}

    public Artist(String name, String nacionality){

        this.name = name;
        this.nacionality = nacionality;

    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
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
