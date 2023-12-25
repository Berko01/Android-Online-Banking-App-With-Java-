package com.example.onlinebankingappproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.ApiAuthService;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView signUpText = findViewById(R.id.sign_up_text);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Burada RegisterActivity'ye geçiş yapılacak
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        // XML dosyasındaki EditText ve Button elemanlarını tanımlama
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        ApiAuthService apiAuthService = new ApiAuthService(this);
        // Giriş yapma butonuna tıklanınca çağrılacak fonksiyonu belirleme
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText elemanlarından e-posta ve şifre bilgilerini alıp değişkenlere atama
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                // API servisini oluştur
                apiAuthService.login(email,password);
                navigateToDashboard();
            }

        });
    }
    private void navigateToDashboard() {
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
        finish(); // LoginActivity'yi geri dönüş yapmasın diye kapat
    }
}