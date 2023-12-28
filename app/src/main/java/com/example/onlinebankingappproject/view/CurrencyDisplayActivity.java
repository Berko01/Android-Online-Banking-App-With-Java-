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
        String apiKey = "your-api-key";
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
                        eurRateTextView.setText(String.format("1 EUR Karşılığı: %.2f ₺", (data.get("TRY")) / (data.get("EUR"))));
                        usdRateTextView.setText(String.format("1 USD Karşılığı: %.2f ₺", data.get("TRY")));
                        jpyRateTextView.setText(String.format("1 JPY Karşılığı: %.2f ₺", (data.get("TRY")) / (data.get("JPY"))));
                        gbpRateTextView.setText(String.format("1 GBP Karşılığı: %.2f ₺", (data.get("TRY")) / (data.get("GBP"))));
                        audRateTextView.setText(String.format("1 AUD Karşılığı: %.2f ₺", (data.get("TRY")) / (data.get("AUD"))));

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
