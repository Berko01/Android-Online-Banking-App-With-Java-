package com.example.onlinebankingappproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.onlinebankingappproject.R;

public class MenuActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button viewDashboardButton = findViewById(R.id.viewDashboardButton);
        Button paymentHistoryButton = findViewById(R.id.paymentHistoryButton);

        viewDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hesapları Görüntüleme sayfasına yönlendirme işlemi
                viewDashBoard(view);
            }
        });
        Button viewCreateAccountButton = findViewById(R.id.viewCreateAccountButton);
        viewCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewCreateAccount(view);
            }
        });
        paymentHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentHistory(view);
            }
        });
    }

    public void viewDashBoard(View view) {
        // Hesapları Görüntüleme sayfasına yönlendirme işlemi
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
    // Hesap Açma sayfasına yönlendirme metod
    public void viewCreateAccount(View view) {
        try {
            Intent intent = new Intent(this, CreateAccountActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.e("viewCreateAccount", "Error starting CreateAccountActivity", e);
        }

    }

    public void paymentHistory(View view) {
        // Ödeme Geçmişi sayfasına yönlendirme işlemi
        Intent intent = new Intent(this, PaymentHistoryActivity.class);
        startActivity(intent);
    }

    public void transferHistory(View view) {
        // Transfer Geçmişi sayfasına yönlendirme işlemi
        Intent intent = new Intent(this, TransactionHistoryActivity.class);
        startActivity(intent);
    }
}