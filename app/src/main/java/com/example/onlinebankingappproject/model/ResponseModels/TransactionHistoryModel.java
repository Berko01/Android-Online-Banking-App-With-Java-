package com.example.onlinebankingappproject.model.ResponseModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionHistoryModel {

    @SerializedName("transaction_id")
    private int transactionId;

    @SerializedName("account_id")
    private int accountId;

    @SerializedName("transaction_type")
    private String transactionType;

    @SerializedName("amount")
    private double amount;

    @SerializedName("source")
    private String source;

    @SerializedName("status")
    private String status;

    @SerializedName("reason_code")
    private String reasonCode;

    @SerializedName("created_at")
    private List<Integer> createdAt;

    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getSource() {
        return source;
    }

    public String getStatus() {
        return status;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public List<Integer> getCreatedAt() {
        return createdAt;
    }
}
