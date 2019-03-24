package com.example.capstone.models;

import org.parceler.Parcel;

@Parcel
public class Popular {
    String name;
    String image_thumbnail_path;
    public Popular(){}

    public Popular (String name, String image_thumbnail_path){
        this.name = name;
        this.image_thumbnail_path = image_thumbnail_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_thumbnail_path() {
        return image_thumbnail_path;
    }

    public void setImage_thumbnail_path(String image_thumbnail_path) {
        this.image_thumbnail_path = image_thumbnail_path;
    }
}
