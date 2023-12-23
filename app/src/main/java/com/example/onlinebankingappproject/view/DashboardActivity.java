package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.ApiAuthService;
import com.example.onlinebankingappproject.Utilities.TokenUtil.LocalStorageManager;
import com.example.onlinebankingappproject.api.ApiGetTransactionService;

public class DashboardActivity extends AppCompatActivity {

    private TextView accessTokenTextView;
    private LocalStorageManager localStorageManager;
    private ApiAuthService apiAuthService;
    private ApiGetTransactionService apiGetTransactionService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Ensure Toolbar is used for the action bar
        Toolbar toolbar = findViewById(R.id.toolbar); // Assuming a Toolbar with ID "toolbar" in your layout
        setSupportActionBar(toolbar);


        apiAuthService = new ApiAuthService(this);
        apiGetTransactionService = new ApiGetTransactionService(this);
        localStorageManager = new LocalStorageManager(this);
        accessTokenTextView = findViewById(R.id.accessTokenTextView);

        // Start Process Button'ını bul
        Button startProcessButton = findViewById(R.id.startProcessButton);

        // Button'a tıklanma event'ini dinle
        startProcessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Butona tıklandığında yapılacak işlemleri burada tanımla
                startProcess();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.signout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        apiAuthService.logout();
        navigateToLogin();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void startProcess() {
        apiGetTransactionService.getDashboardAsync().thenAccept(responseData -> {
            // Elde edilen responseData'i kullan
            System.out.println("Response Data: " + responseData.getUserAccounts() + " " + responseData.getTotalBalance());
            accessTokenTextView.setText(responseData.getTotalBalance().toString());
        }).exceptionally(ex -> {
            // Hata durumunu ele al
            System.err.println("Error: " + ex.getMessage());
            return null;
        });
    }
}
