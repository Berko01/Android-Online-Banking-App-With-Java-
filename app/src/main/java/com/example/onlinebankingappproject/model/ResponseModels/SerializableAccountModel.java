package com.example.onlinebankingappproject.model.ResponseModels;

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
