package com.example.onlinebankingappproject.model.request_models;

import com.google.gson.annotations.SerializedName;

public class PaymentRequestModel {
    @SerializedName("beneficiary")
    private String beneficiary;

    @SerializedName("account_number")
    private String account_number;
    @SerializedName("account_id")
    private String account_id;

    @SerializedName("reference")
    private String reference;
    @SerializedName("payment_amount")
    private String payment_amount;


    public PaymentRequestModel(String beneficiary, String account_number, String account_id, String reference, String payment_amount) {
        this.beneficiary = beneficiary;
        this.account_number = account_number;
        this.account_id = account_id;
        this.reference = reference;
        this.payment_amount = payment_amount;
    }
}
