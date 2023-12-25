package com.example.onlinebankingappproject.model.request_models;

import com.google.gson.annotations.SerializedName;

public class CreateAccountRequestModel {

    @SerializedName("account_name")
    private String account_name;
    @SerializedName("account_type")
    private String account_type;


    public CreateAccountRequestModel(String account_name, String account_type) {
        this.account_name = account_name;
        this.account_type = account_type;
    }
}
