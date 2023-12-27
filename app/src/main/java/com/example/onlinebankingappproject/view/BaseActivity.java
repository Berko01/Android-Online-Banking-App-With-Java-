// BaseActivity.java
package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.ApiAuthService;

import java.util.concurrent.CompletableFuture;

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
        // Perform logout asynchronously
        CompletableFuture<Void> logoutFuture = apiAuthService.logout();

        // Handle the result or exception when the logout operation completes
        logoutFuture.whenComplete((result, throwable) -> {
            if (throwable == null) {
                // Logout successful, navigate to login
                Toast.makeText(BaseActivity.this, "Logout başarılı.", Toast.LENGTH_SHORT).show();
                navigateToLogin();
            } else {
                // Logout failed, handle the exception (display an error message, etc.)
                Toast.makeText(BaseActivity.this, "Logout başarısız.", Toast.LENGTH_SHORT).show();
                throwable.printStackTrace();
                // You may choose to display an error message to the user
            }
        });
    }

    protected void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
