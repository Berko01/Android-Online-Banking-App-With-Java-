package com.example.onlinebankingappproject.model.ResponseModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterResponseModel {
    @SerializedName("message")
    private String message;

    @SerializedName("user")
    private UserModel user;

    public String getMessage() {
        return message;
    }

    public UserModel getUser() {
        return user;
    }
}
