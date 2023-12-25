package com.example.onlinebankingappproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.ExchangeRatesApiGetRequestService;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // API key, base currency ve hedef currencies parametrelerini belirle
        String apiKey = "fca_live_USJmqSs42YgQR6NqN7rkZBmyc1ISvCmBI7WeWWih";
        String baseCurrency = "TRY";
        String currencies = "EUR,USD,JPY,GBP,AUD";

        // ExchangeRatesApiGetRequestService oluştur
        ExchangeRatesApiGetRequestService apiGetRequestService = new ExchangeRatesApiGetRequestService();

        // CompletableFuture ile asenkron bir şekilde API'yi çağır
        CompletableFuture<Map<String, Double>> currencyFuture = apiGetRequestService.getLatestCurrencies(apiKey, baseCurrency, currencies);

        // Belirli bir süre bekleyerek Splash Screen'ı göster
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // CompletableFuture'nin tamamlanmasını bekleyerek sonuçları işle
                currencyFuture.thenAccept(data -> {
                    double eurRate = data.get("EUR");
                    double usdRate = data.get("USD");
                    double jpyRate = data.get("JPY");
                    double gbpRate = data.get("GBP");
                    double audRate = data.get("AUD");
                    // Verileri kullan
                    System.out.println("EUR Rate: " + eurRate);
                    System.out.println("USD Rate: " + usdRate);
                    System.out.println("JPY Rate: " + jpyRate);
                    System.out.println("GBP Rate: " + gbpRate);
                    System.out.println("AUD Rate: " + audRate);

                    // API isteği tamamlandıktan sonra LoginActivity'ye yönlendir
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }).exceptionally(e -> {
                    // Hata durumunda işlemler
                    System.err.println("Request failed: " + e.getMessage());
                    // Hata durumunda bile LoginActivity'ye yönlendir
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                    return null;
                });
            }
        }, 2500);
    }
}
