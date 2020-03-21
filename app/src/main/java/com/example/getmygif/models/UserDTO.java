package com.example.getmygif.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class UserDTO {
    @SerializedName("avatar_url")
    @Expose
    private String photoUrl;
}
