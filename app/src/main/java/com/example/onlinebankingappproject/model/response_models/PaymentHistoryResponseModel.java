package com.example.onlinebankingappproject.model.response_models;

import com.example.onlinebankingappproject.model.base_models.PaymentHistoryModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentHistoryResponseModel {
    @SerializedName("payment_history")
    private List<PaymentHistoryModel> paymentHistory;

    public List<PaymentHistoryModel> getPaymentHistory() {
        return paymentHistory;
    }
}
