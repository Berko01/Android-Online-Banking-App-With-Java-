package com.example.onlinebankingappproject.api.Client;

import com.example.onlinebankingappproject.api.ExchangeRatesApiServiceInterface;
import com.example.onlinebankingappproject.model.response_models.CurrencyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExchangeRatesApiClient {

    private static final String BASE_URL = "https://api.freecurrencyapi.com/";

    private ExchangeRatesApiServiceInterface apiService;

    // Constructor, ExchangeRatesApiClient sınıfını başlatır ve Retrofit istemcisini oluşturur
    public ExchangeRatesApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // JSON verisini Java nesnelerine dönüştürmek için Gson kullanılır
                .build();

        apiService = retrofit.create(ExchangeRatesApiServiceInterface.class);
    }

    // En güncel döviz kurlarını almak için API'yi çağıran metot
    public void getLatestCurrencies(String apiKey, String baseCurrency, String currencies, Callback<CurrencyResponse> callback) {
        // API'ye istek yapmak için Retrofit Call nesnesi oluşturulur
        Call<CurrencyResponse> call = apiService.getLatestCurrencies(apiKey, baseCurrency, currencies);

        // Asenkron olarak API'ye istek yapılır ve sonucu belirtilen geriçağırma (callback) fonksiyonuna iletir
        call.enqueue(callback);
    }
}
