package com.example.onlinebankingappproject.model.response_models;

import com.example.onlinebankingappproject.model.base_models.UserModel;
import com.google.gson.annotations.SerializedName;

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
