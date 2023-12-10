package com.example.onlinebankingappproject.model.RequestModels;

import com.google.gson.annotations.SerializedName;

public class LoginRequestModel {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LoginRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // constructor, getter ve setter metodları
}
