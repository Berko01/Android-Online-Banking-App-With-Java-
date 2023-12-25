package com.example.onlinebankingappproject.model.response_models;

import com.example.onlinebankingappproject.model.base_models.TransactionHistoryModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransactionHistoryResponseModel {

    @SerializedName("transaction_history")
    private List<TransactionHistoryModel> transactionHistory;

    public List<TransactionHistoryModel> getTransactionHistory() {
        return transactionHistory;
    }

}
