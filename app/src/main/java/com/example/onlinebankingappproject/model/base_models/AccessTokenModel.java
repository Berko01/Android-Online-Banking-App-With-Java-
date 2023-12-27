package com.example.onlinebankingappproject.model.base_models;

import com.google.gson.annotations.SerializedName;

public class AccessTokenModel {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("message")
    private String message;

    public AccessTokenModel(String accessToken, String message) {
        this.accessToken = accessToken;
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getMessage() {
        return message;
    }
}
