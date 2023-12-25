package com.example.onlinebankingappproject.model.request_models;

import com.google.gson.annotations.SerializedName;

public class RegisterRequestModel {
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;


    public RegisterRequestModel(String first_name, String last_name, String email, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }
}
