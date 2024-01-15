package com.example.onlinebankingappproject.model.base_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Finansal işlemlerin detaylarını temsil eden sınıf.
 * Her bir işlemin özelliklerini içerir.
 */
public class TransactionHistoryModel {
    @SerializedName("transaction_id")
    private int transactionId; // İşlem kimliği

    @SerializedName("account_id")
    private int accountId; // İşlemin gerçekleştiği hesabın kimliği

    @SerializedName("transaction_type")
    private String transactionType; // İşlem türü (örneğin, "deposit", "withdraw", "transfer", "payment" vb.)

    @SerializedName("amount")
    private double amount; // İşlem miktarı

    @SerializedName("source")
    private String source; // İşlemin kaynağı (örneğin, transfer işlemlerinde kaynak hesap, ödeme işlemlerinde ödeme yapılacak hesap vb.)

    @SerializedName("status")
    private String status; // İşlem durumu (örneğin, "success", "failed" vb.)

    @SerializedName("reason_code")
    private String reasonCode; // İşlem durumu neden kodu (hata durumlarında kullanılabilir)

    @SerializedName("created_at")
    private List<Integer> createdAt; // İşlemin oluşturulma tarihi ve saati

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
