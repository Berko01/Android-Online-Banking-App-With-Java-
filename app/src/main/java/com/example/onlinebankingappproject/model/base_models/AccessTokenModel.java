package com.example.onlinebankingappproject.model.base_models;

import com.google.gson.annotations.SerializedName;

public class AccessTokenModel {
    // Erişim anahtarı (token) verisini temsil eder.
    @SerializedName("access_token")
    private String accessToken;

    // Sunucudan dönen mesajı temsil eder.
    @SerializedName("message")
    private String message;

    // AccessTokenModel sınıfının yapıcı metodu.
    public AccessTokenModel(String accessToken, String message) {
        this.accessToken = accessToken;
        this.message = message;
    }

    // Erişim anahtarını getirmek için kullanılır.
    public String getAccessToken() {
        return accessToken;
    }

    // Sunucudan dönen mesajı getirmek için kullanılır.
    public String getMessage() {
        return message;
    }
}
