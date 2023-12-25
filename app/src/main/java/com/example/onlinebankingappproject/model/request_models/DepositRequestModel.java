package com.example.onlinebankingappproject.model.request_models;

import com.google.gson.annotations.SerializedName;

public class DepositRequestModel {
    @SerializedName("deposit_amount")
    private String deposit_amount;
    @SerializedName("account_id")
    private int account_id;


    public DepositRequestModel(String deposit_amount, int account_id) {
        this.deposit_amount = deposit_amount;
        this.account_id = account_id;
    }
}
