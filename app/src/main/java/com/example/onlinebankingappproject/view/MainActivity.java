package com.example.onlinebankingappproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.ApiService;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML dosyasındaki EditText ve Button elemanlarını tanımlama
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Giriş yapma butonuna tıklanınca çağrılacak fonksiyonu belirleme
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText elemanlarından e-posta ve şifre bilgilerini alıp değişkenlere atama
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();



                // API servisini oluştur
                ApiService apiService = new ApiService();

                apiService.login(email,password);

            }
        });
    }
}