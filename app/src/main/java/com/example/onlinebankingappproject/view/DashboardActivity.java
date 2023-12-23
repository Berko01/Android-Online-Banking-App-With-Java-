package com.example.onlinebankingappproject.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.ApiAuthService;
import com.example.onlinebankingappproject.Utilities.TokenUtil.LocalStorageManager;

public class DashboardActivity extends AppCompatActivity {

    private TextView accessTokenTextView;
    private LocalStorageManager localStorageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // LocalStorageManager'ı başlat
        LocalStorageManager localStorageManager = new LocalStorageManager(this);

        accessTokenTextView = findViewById(R.id.accessTokenTextView);


        // AccessTokenManager'dan token al ve TextView'e yaz
        String accessToken = localStorageManager.getAccessToken();
        accessTokenTextView.setText("Access Token: " + accessToken);
    }
}