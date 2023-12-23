package com.example.onlinebankingappproject.model.ResponseModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionHistoryResponseModel {

    @SerializedName("transaction_history")
    private List<TransactionHistoryModel> transactionHistory;

    public List<TransactionHistoryModel> getTransactionHistory() {
        return transactionHistory;
    }

}
