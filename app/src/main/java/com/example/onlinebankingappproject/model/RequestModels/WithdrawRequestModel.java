package com.example.onlinebankingappproject.model.RequestModels;

import com.google.gson.annotations.SerializedName;

public class WithdrawRequestModel {
    @SerializedName("withdrawal_amount")
    private String withdrawal_amount;
    @SerializedName("account_id")
    private String account_id;

    public WithdrawRequestModel(String withdrawal_amount, String account_id) {
        this.withdrawal_amount = withdrawal_amount;
        this.account_id = account_id;
    }
}
