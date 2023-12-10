package com.example.onlinebankingappproject.model.RequestModels;

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


}
