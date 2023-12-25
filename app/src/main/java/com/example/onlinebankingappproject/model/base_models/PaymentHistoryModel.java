package com.example.onlinebankingappproject.model.base_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    private List<Integer> createdAtList;
    public int getPaymentId() {
        return paymentId;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public String getBeneficiaryAccNo() {
        return beneficiaryAccNo;
    }

    public double getAmount() {
        return amount;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public String getStatus() {
        return status;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public List<Integer>getCreatedAt() {
        return createdAtList;
    }
}
