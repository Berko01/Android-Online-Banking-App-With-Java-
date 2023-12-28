// RegisterActivity.java
package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.Service.ApiAuthService;
import com.example.onlinebankingappproject.model.response_models.RegisterResponseModel;

import java.util.concurrent.CompletableFuture;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private ApiAuthService apiAuthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // XML dosyasındaki EditText ve Button elemanlarını tanımlama
        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        registerButton = findViewById(R.id.submit_button);

        // API servisi nesnesini oluştur
        apiAuthService = new ApiAuthService(this);

        // Kayıt ol butonuna tıklanınca çağrılacak fonksiyonu belirleme
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText elemanlarından bilgileri alıp değişkenlere atama
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Perform registration asynchronously
                CompletableFuture<RegisterResponseModel> registrationFuture = apiAuthService.register(firstName, lastName, email, password);

                // Handle the result or exception when the registration operation completes
                registrationFuture.whenComplete((registerResponseModel, throwable) -> {
                    if (throwable == null) {
                        // Registration successful, handle the response if needed
                        // For example, you can display a success message
                        Toast.makeText(RegisterActivity.this, "Kayıt başarılı. Mail adresinizden hesabı onaylamayı unutmayın Giriş yapabilirsiniz.", Toast.LENGTH_SHORT).show();

                        // Navigate to the login screen
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // This line prevents returning to the registration screen with the back button
                    } else {
                        // Registration failed, handle the exception (display an error message, etc.)
                        // For example, you can display an error message
                        Toast.makeText(RegisterActivity.this, "Kayıt başarısız. Lütfen tekrar deneyin.", Toast.LENGTH_SHORT).show();
                        throwable.printStackTrace();
                    }
                });
            }
        });
    }
}