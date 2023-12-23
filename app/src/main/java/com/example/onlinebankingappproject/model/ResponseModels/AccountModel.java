package com.example.onlinebankingappproject.model.ResponseModels;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AccountModel {
    @SerializedName("account_id")
    private  int account_id;
    @SerializedName("user_id")
    private int user_id;
    @SerializedName("account_number")
    private String account_number;
    @SerializedName("account_name")
    private String account_name;
    @SerializedName("account_type")
    private String account_type;
    @SerializedName("balance")
    private BigDecimal balance;
    @SerializedName("create_at")

    private LocalDateTime create_at;

    @SerializedName("updated_at")
    private List<Integer> updated_at;

    public AccountModel(int account_id, int user_id, String account_number, String account_name,
                        String account_type, BigDecimal balance, LocalDateTime create_at, List<Integer> updated_at) {
        this.account_id = account_id;
        this.user_id = user_id;
        this.account_number = account_number;
        this.account_name = account_name;
        this.account_type = account_type;
        this.balance = balance;
        this.create_at = create_at;
        this.updated_at = updated_at;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public List<Integer> getUpdated_at() {
        return updated_at;
    }
}
