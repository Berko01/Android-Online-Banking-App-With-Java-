package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.ApiAuthService;
import com.example.onlinebankingappproject.view.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;

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

        // Kayıt ol butonuna tıklanınca çağrılacak fonksiyonu belirleme
        ApiAuthService apiAuthService = new ApiAuthService(this);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText elemanlarından bilgileri alıp değişkenlere atama
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // API servisini oluştur

                // Register işlemini gerçekleştir
                apiAuthService.register(firstName, lastName, email, password);

                if (true) {
                    // Kayıt başarılı ise kullanıcıyı giriş ekranına yönlendir
                    Toast.makeText(RegisterActivity.this, "Kayıt başarılı. Mail adresinizden hesabı onaylamayı unutmayın Giriş yapabilirsiniz.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // Bu satır, geri tuşu ile kayıt ekranına dönülmesini engeller
                } else {
                    // Kayıt başarısız ise kullanıcıyı bilgilendir
                    Toast.makeText(RegisterActivity.this, "Kayıt başarısız. Lütfen tekrar deneyin.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
