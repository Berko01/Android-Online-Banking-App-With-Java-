package com.example.onlinebankingappproject.model.base_models;

import com.example.onlinebankingappproject.model.base_models.AccountModel;

import java.io.Serializable;

public class SerializableAccountModel implements Serializable {
    private AccountModel accountModel;

    public SerializableAccountModel(AccountModel accountModel) {
        this.accountModel = accountModel;
    }

    public AccountModel getAccountModel() {
        return accountModel;
    }
}
