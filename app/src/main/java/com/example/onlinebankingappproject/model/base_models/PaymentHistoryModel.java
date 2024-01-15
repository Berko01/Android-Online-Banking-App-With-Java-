package com.example.onlinebankingappproject.model.base_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentHistoryModel {
    // Ödeme geçmişi öğesinin benzersiz kimliğini temsil eder.
    @SerializedName("payment_id")
    private int paymentId;

    // Hesap ID'sini temsil eder, bu ödemenin ilişkilendirildiği hesap.
    @SerializedName("account_id")
    private int accountId;

    // Ödemenin alıcı adını temsil eder.
    @SerializedName("beneficiary")
    private String beneficiary;

    // Alıcı hesap numarasını temsil eder.
    @SerializedName("beneficiary_acc_no")
    private String beneficiaryAccNo;

    // Ödeme miktarını temsil eder.
    @SerializedName("amount")
    private double amount;

    // Ödeme referans numarasını temsil eder.
    @SerializedName("reference_no")
    private String referenceNo;

    // Ödeme durumunu temsil eder (örneğin, başarılı, başarısız).
    @SerializedName("status")
    private String status;

    // Ödeme başarısız olduğunda kullanılan neden kodunu temsil eder.
    @SerializedName("reason_code")
    private String reasonCode;

    // Ödemenin oluşturulma tarihlerini temsil eder.
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

    public List<Integer> getCreatedAt() {
        return createdAtList;
    }
}
