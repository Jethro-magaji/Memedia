package com.memes.memedia.models;

public class MemeTemplate {

    private String imageUrl;
    private int id;

    public MemeTemplate(String imageUrl, int id) {
        this.imageUrl = imageUrl;
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
