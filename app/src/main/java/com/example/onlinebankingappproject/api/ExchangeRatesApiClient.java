package com.example.onlinebankingappproject.api;

import com.example.onlinebankingappproject.model.response_models.CurrencyResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExchangeRatesApiClient {

    private static final String BASE_URL = "https://api.freecurrencyapi.com/";

    private ExchangeRatesApiServiceInterface apiService;

    public ExchangeRatesApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ExchangeRatesApiServiceInterface.class);
    }

    public void getLatestCurrencies(String apiKey, String baseCurrency, String currencies, Callback<CurrencyResponse> callback) {
        Call<CurrencyResponse> call = apiService.getLatestCurrencies(apiKey, baseCurrency, currencies);
        call.enqueue(callback);
    }
}
