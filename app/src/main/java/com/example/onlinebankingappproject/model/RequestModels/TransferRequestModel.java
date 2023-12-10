package com.example.onlinebankingappproject.model.RequestModels;

import com.google.gson.annotations.SerializedName;

public class TransferRequestModel {
    @SerializedName("sourceAccount")
    private String sourceAccount;
    @SerializedName("targetAccount")
    private String targetAccount;

    @SerializedName("amount")
    private String amount;

    public TransferRequestModel(String sourceAccount, String targetAccount, String amount) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
    }
}
