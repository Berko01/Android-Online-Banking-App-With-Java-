package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.Service.ApiPostTransactionService;
import com.example.onlinebankingappproject.model.base_models.AccountModel;

import java.util.concurrent.CompletableFuture;

public class CreateAccountActivity extends BaseActivity {

    private EditText accountNameEditText, accountTypeEditText;
    private Button createAccountButton;

    ApiPostTransactionService apiPostTransactionService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        accountNameEditText = findViewById(R.id.accountNameEditText);
        accountTypeEditText = findViewById(R.id.accountTypeEditText);
        createAccountButton = findViewById(R.id.createAccountButton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        apiPostTransactionService = new ApiPostTransactionService(this);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hesap açma butonuna tıklanınca yapılacak işlemler
                String accountName = accountNameEditText.getText().toString();
                String accountType = accountTypeEditText.getText().toString();

                // Hesap açma isteğini gönder
                sendCreateAccountRequest(accountName, accountType);
            }
        });
    }

    private void sendCreateAccountRequest(String accountName, String accountType) {
        // Hesap açma isteği gönder
        CompletableFuture<AccountModel> future = apiPostTransactionService.createAccount(accountName, accountType);

        // Asenkron işlemleri takip et
        future.thenAccept(responseData -> {
            // Hesap açma başarılı ise
            Toast.makeText(CreateAccountActivity.this, "Hesap başarıyla açıldı: " + responseData.getAccount_name(), Toast.LENGTH_SHORT).show();
            // İsterseniz bu noktada başka bir aktiviteye geçiş yapabilirsiniz.
        }).exceptionally(ex -> {
            // Hesap açma başarısız ise
            Toast.makeText(CreateAccountActivity.this, "Hesap açma işlemi başarısız oldu", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            return null;
        });
    }
}
