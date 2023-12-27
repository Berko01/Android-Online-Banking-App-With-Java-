package com.example.onlinebankingappproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import android.widget.Toast;


import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.Utilities.TokenUtil.LocalStorageManager;
import com.example.onlinebankingappproject.api.ApiAuthService;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    private LocalStorageManager localStorageManager;
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
        localStorageManager = new LocalStorageManager(this);
        ApiAuthService apiAuthService = new ApiAuthService(this);
        // Giriş yapma butonuna tıklanınca çağrılacak fonksiyonu belirleme
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText elemanlarından e-posta ve şifre bilgilerini alıp değişkenlere atama
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                localStorageManager.saveAccessToken(null);
                // API servisini oluştur
                apiAuthService.login(email, password);


                navigateToDashboard();
                if(email.equals("")&&password.equals("")){
                    Toast.makeText(LoginActivity.this, "E-posta veya Şifre boşluk olmamalıdır.", Toast.LENGTH_SHORT).show();
                }else {
                    apiAuthService.login(email,password);
                    if (localStorageManager.getAccessToken()!=null){
                        navigateToDashboard();
                    }else{
                        Toast.makeText(LoginActivity.this, "Şifreniz veya Parolanız hatali lütfen tekrar deneyiniz", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });
    }




    private void navigateToDashboard() {
        Toast.makeText(LoginActivity.this, "Giris basarili.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
        finish(); // LoginActivity'yi geri dönüş yapmasın diye kapat
    }

    public void openFinancialData(View view) {
        Intent intent = new Intent(this, CurrencyDisplayActivity.class);
        startActivity(intent);
    }

}