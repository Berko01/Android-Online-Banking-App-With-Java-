package com.example.onlinebankingappproject.model.ResponseModels;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class PaymentHistoryModel {
    @SerializedName("payment_id")
    private int paymentId;
    @SerializedName("account_id")
    private int accountId;
    @SerializedName("beneficiary")
    private String beneficiary;
    @SerializedName("beneficiary_acc_no")
    private String beneficiaryAccNo;
    @SerializedName("amount")
    private double amount;
    @SerializedName("reference_no")
    private String referenceNo;
    @SerializedName("status")
    private String status;
    @SerializedName("reason_code")
    private String reasonCode;
    @SerializedName("created_at")
    private LocalDateTime createdAt;
}
