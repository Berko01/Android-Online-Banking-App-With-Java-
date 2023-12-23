package com.example.onlinebankingappproject.Utilities.TokenUtil;

public class AccessTokenManager {
    private static AccessTokenManager instance;
    private String accessToken;

    private AccessTokenManager() {
        // private constructor to prevent instantiation
    }

    public static AccessTokenManager getInstance() {
        if (instance == null) {
            instance = new AccessTokenManager();
        }
        return instance;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String token) {
        this.accessToken = token;
    }
}
