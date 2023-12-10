package com.example.onlinebankingappproject.model.ResponseModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionResponseModel {

    @SerializedName("user")
    private List<AccountModel> userAccounts;

    public TransactionResponseModel(List<AccountModel> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public List<AccountModel> getUserAccounts() {
        return userAccounts;
    }
}
