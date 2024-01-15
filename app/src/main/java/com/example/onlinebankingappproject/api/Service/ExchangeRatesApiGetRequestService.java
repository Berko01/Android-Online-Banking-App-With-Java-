package com.example.onlinebankingappproject.api.Service;

import com.example.onlinebankingappproject.api.Client.ExchangeRatesApiClient;
import com.example.onlinebankingappproject.model.response_models.CurrencyResponse;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Map;

public class ExchangeRatesApiGetRequestService {
    private ExchangeRatesApiClient currencyApiClient;

    // ExchangeRatesApiGetRequestService sınıfının kurucu metodu
    public ExchangeRatesApiGetRequestService() {
        this.currencyApiClient = new ExchangeRatesApiClient();
    }

    // En güncel döviz kuru bilgilerini asenkron olarak almak için kullanılan metot
    public CompletableFuture<Map<String, Double>> getLatestCurrencies(
            String apiKey, String baseCurrency, String currencies) {
        // Asenkron sonuçları işlemek için CompletableFuture oluşturuluyor
        CompletableFuture<Map<String, Double>> future = new CompletableFuture<>();

        // ExchangeRatesApiClient sınıfı kullanılarak API isteği yapılıyor
        currencyApiClient.getLatestCurrencies(
                apiKey,
                baseCurrency,
                currencies,
                new Callback<CurrencyResponse>() {
                    @Override
                    public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                        // Başarılı bir cevap durumunda işlemler yapılır
                        if (response.isSuccessful()) {
                            // Cevap gövdesinden döviz kuru verileri çıkarılır
                            Map<String, Double> data = response.body().getData();
                            // CompletableFuture, elde edilen veri ile tamamlanır
                            future.complete(data);
                        } else {
                            // Başarısız durumda CompletableFuture istisna ile tamamlanır
                            future.completeExceptionally(new RuntimeException("İstek başarılı değil."));
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                        // Başarısız durumda CompletableFuture istisna ile tamamlanır
                        future.completeExceptionally(t);
                    }
                }
        );

        // CompletableFuture'nin ileri işlemler veya çağırıcı tarafından işlenmesi için geri döndürülür
        return future;
    }
}
