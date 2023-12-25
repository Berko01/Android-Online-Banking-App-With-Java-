package com.example.onlinebankingappproject.model.response_models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CurrencyResponse {
    @SerializedName("data")
    private Map<String, Double> data;

    public Map<String, Double> getData() {
        return data;
    }
}
