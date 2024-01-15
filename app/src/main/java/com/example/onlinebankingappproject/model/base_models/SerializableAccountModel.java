package com.example.onlinebankingappproject.model.base_models;

import com.example.onlinebankingappproject.model.base_models.AccountModel;

import java.io.Serializable;

public class SerializableAccountModel implements Serializable {
    // SerializableAccountModel'in içinde bulunan AccountModel nesnesi.
    private AccountModel accountModel;

    // SerializableAccountModel sınıfının yapıcı metodu.
    public SerializableAccountModel(AccountModel accountModel) {
        // AccountModel nesnesini alarak yeni bir SerializableAccountModel oluşturur.
        this.accountModel = accountModel;
    }

    // AccountModel nesnesini döndüren get metodu.
    public AccountModel getAccountModel() {
        return accountModel;
    }
}
