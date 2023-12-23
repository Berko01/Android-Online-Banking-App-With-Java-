package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.ApiAuthService;
import com.example.onlinebankingappproject.Utilities.TokenUtil.LocalStorageManager;

public class DashboardActivity extends AppCompatActivity {

    private TextView accessTokenTextView;
    private LocalStorageManager localStorageManager;
    private ApiAuthService apiAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Ensure Toolbar is used for the action bar
        Toolbar toolbar = findViewById(R.id.toolbar); // Assuming a Toolbar with ID "toolbar" in your layout
        setSupportActionBar(toolbar);

        apiAuthService = new ApiAuthService(this);
        localStorageManager = new LocalStorageManager(this);
        accessTokenTextView = findViewById(R.id.accessTokenTextView);

        String accessToken = localStorageManager.getAccessToken();
        accessTokenTextView.setText("Access Token: " + accessToken);
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
}
