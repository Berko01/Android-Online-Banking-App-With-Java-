package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.ExchangeRatesApiGetRequestService;

import java.util.Map;

public class CurrencyDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_display);

        // API key, base currency ve hedef currencies parametrelerini belirle
        String apiKey = "fca_live_USJmqSs42YgQR6NqN7rkZBmyc1ISvCmBI7WeWWih";
        String baseCurrency = "USD";
        String currencies = "EUR,TRY,JPY,GBP,AUD";

        // ExchangeRatesApiGetRequestService oluştur
        ExchangeRatesApiGetRequestService apiGetRequestService = new ExchangeRatesApiGetRequestService();

        // API'den verileri al
        apiGetRequestService.getLatestCurrencies(apiKey, baseCurrency, currencies)
                .thenAccept(data -> {
                    // Layout'taki TextView'leri bul
                    TextView eurRateTextView = findViewById(R.id.eurRateTextView);
                    TextView usdRateTextView = findViewById(R.id.usdRateTextView);
                    TextView jpyRateTextView = findViewById(R.id.jpyRateTextView);
                    TextView gbpRateTextView = findViewById(R.id.gbpRateTextView);
                    TextView audRateTextView = findViewById(R.id.audRateTextView);

                    // TextView'lere verileri yaz
                    runOnUiThread(() -> {
                        eurRateTextView.setText("EUR Rate: " + (data.get("TRY"))/(data.get("EUR")));
                        usdRateTextView.setText("USD Rate: " + data.get("TRY"));
                        jpyRateTextView.setText("JPY Rate: " + (data.get("TRY"))/(data.get("JPY")));
                        gbpRateTextView.setText("GBP Rate: " + (data.get("TRY"))/(data.get("GBP")));
                        audRateTextView.setText("AUD Rate: " + (data.get("TRY"))/(data.get("AUD")));
                    });
                })
                .exceptionally(e -> {
                    // Hata durumunda işlemler
                    System.err.println("Request failed: " + e.getMessage());
                    // Hata durumunda bile LoginActivity'ye yönlendir
                    startActivity(new Intent(CurrencyDisplayActivity.this, LoginActivity.class));
                    finish();
                    return null;
                });
    }
}
