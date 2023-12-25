package com.example.onlinebankingappproject.api;

import com.example.onlinebankingappproject.model.response_models.CurrencyResponse;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Map;

public class ExchangeRatesApiGetRequestService {

    private ExchangeRatesApiClient currencyApiClient;

    public ExchangeRatesApiGetRequestService() {
        this.currencyApiClient = new ExchangeRatesApiClient();
    }

    public CompletableFuture<Map<String, Double>> getLatestCurrencies(
            String apiKey, String baseCurrency, String currencies) {
        CompletableFuture<Map<String, Double>> future = new CompletableFuture<>();

        currencyApiClient.getLatestCurrencies(
                apiKey,
                baseCurrency,
                currencies,
                new Callback<CurrencyResponse>() {
                    @Override
                    public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                        if (response.isSuccessful()) {
                            Map<String, Double> data = response.body().getData();
                            future.complete(data);
                        } else {
                            future.completeExceptionally(new RuntimeException("Request was not successful."));
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                        future.completeExceptionally(t);
                    }
                }
        );

        return future;
    }
}


