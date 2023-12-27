// LoginActivity.java
package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.ApiAuthService;
import com.example.onlinebankingappproject.Utilities.TokenUtil.ApiAuthException;
import com.example.onlinebankingappproject.model.base_models.AccessTokenModel;

import java.util.concurrent.CompletableFuture;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView signUpText;
    private ApiAuthService apiAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpText = findViewById(R.id.sign_up_text);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        apiAuthService = new ApiAuthService(this);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Transition to RegisterActivity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                // Perform login asynchronously
                CompletableFuture<AccessTokenModel> loginFuture = apiAuthService.login(email, password);

                // Handle the result or exception when the login operation completes
                loginFuture.whenComplete((accessTokenModel, throwable) -> {
                    if (throwable == null) {
                        // Login successful, show success message and navigate to the dashboard
                        showToastMessage("Login successful");
                        navigateToDashboard();
                    } else {
                        // Login failed, handle the exception and show error message
                        if (throwable instanceof ApiAuthException) {
                            // Handle specific authentication exception
                            String errorMessage = throwable.getMessage();
                            showToastMessage(errorMessage);
                        } else {
                            // Handle other exceptions
                            showToastMessage("Login failed. Please try again.");
                            throwable.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void showToastMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void navigateToDashboard() {
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
        finish(); // Close LoginActivity to prevent returning to it
    }

    public void openFinancialData(View view) {
        Intent intent = new Intent(this, CurrencyDisplayActivity.class);
        startActivity(intent);
    }
}
