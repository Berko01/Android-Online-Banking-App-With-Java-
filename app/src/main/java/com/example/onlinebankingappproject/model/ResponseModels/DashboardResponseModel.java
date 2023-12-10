package com.example.onlinebankingappproject.model.ResponseModels;



import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class DashboardResponseModel {

    @SerializedName("totalBalance")
    private BigDecimal totalBalance;

    @SerializedName("user")
    private List<AccountModel> userAccounts;

    public DashboardResponseModel(BigDecimal totalBalance, List<AccountModel> userAccounts) {
        this.totalBalance = totalBalance;
        this.userAccounts = userAccounts;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public List<AccountModel> getUserAccounts() {
        return userAccounts;
    }
}
