package com.example.getmygif.models;

public class GigDTO {
    private UserDTO user;
    private ImageDTO images;

    public String getUrl() {
        return images.getOriginal().getUrl();
    }
}
