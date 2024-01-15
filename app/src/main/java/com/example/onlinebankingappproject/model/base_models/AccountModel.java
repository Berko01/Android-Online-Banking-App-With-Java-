package com.example.onlinebankingappproject.model.base_models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AccountModel implements Serializable {
    // Hesap ID'sini temsil eder.
    @SerializedName("account_id")
    private int account_id;

    // Kullanıcı ID'sini temsil eder.
    @SerializedName("user_id")
    private int user_id;

    // Hesap numarasını temsil eder.
    @SerializedName("account_number")
    private String account_number;

    // Hesap adını temsil eder.
    @SerializedName("account_name")
    private String account_name;

    // Hesap türünü temsil eder.
    @SerializedName("account_type")
    private String account_type;

    // Hesap bakiyesini temsil eder.
    @SerializedName("balance")
    private BigDecimal balance;

    // Hesap oluşturulma tarihini temsil eder.
    @SerializedName("create_at")
    private LocalDateTime create_at;

    // Hesap güncellenme tarihlerini temsil eder.
    @SerializedName("updated_at")
    private List<Integer> updated_at;

    // AccountModel sınıfının yapıcı metodu.
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

    // Hesap ID'sini getirmek için kullanılır.
    public int getAccount_id() {
        return account_id;
    }

    // Hesap ID'sini ayarlamak için kullanılır.
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    // Kullanıcı ID'sini getirmek için kullanılır.
    public int getUser_id() {
        return user_id;
    }

    // Kullanıcı ID'sini ayarlamak için kullanılır.
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    // Hesap numarasını getirmek için kullanılır.
    public String getAccount_number() {
        return account_number;
    }

    // Hesap numarasını ayarlamak için kullanılır.
    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    // Hesap adını getirmek için kullanılır.
    public String getAccount_name() {
        return account_name;
    }

    // Hesap adını ayarlamak için kullanılır.
    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    // Hesap türünü getirmek için kullanılır.
    public String getAccount_type() {
        return account_type;
    }

    // Hesap türünü ayarlamak için kullanılır.
    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    // Hesap bakiyesini getirmek için kullanılır.
    public BigDecimal getBalance() {
        return balance;
    }

    // Hesap bakiyesini ayarlamak için kullanılır.
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    // Hesap oluşturulma tarihini getirmek için kullanılır.
    public LocalDateTime getCreate_at() {
        return create_at;
    }

    // Hesap oluşturulma tarihini ayarlamak için kullanılır.
    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    // Hesap güncellenme tarihlerini getirmek için kullanılır.
    public List<Integer> getUpdated_at() {
        return updated_at;
    }
}
