package com.example.onlinebankingappproject.model.response_models;

import com.example.onlinebankingappproject.model.base_models.AccountModel;
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
