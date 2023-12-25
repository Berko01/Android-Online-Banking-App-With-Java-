package com.example.onlinebankingappproject.api;

import com.example.onlinebankingappproject.model.response_models.CurrencyResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ExchangeRatesApiServiceInterface {

    @GET("v1/latest")
    Call<CurrencyResponse> getLatestCurrencies(
            @Query("apikey") String apiKey,
            @Query("base_currency") String baseCurrency,
            @Query("currencies") String currencies
    );
}
