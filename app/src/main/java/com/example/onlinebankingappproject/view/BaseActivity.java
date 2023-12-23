// BaseActivity.java
package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.ApiAuthService;

public class BaseActivity extends AppCompatActivity {

    protected ApiAuthService apiAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiAuthService = new ApiAuthService(this);
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

    void logout() {
        apiAuthService.logout();
        navigateToLogin();
    }

    protected void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
