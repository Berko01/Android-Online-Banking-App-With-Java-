package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.Utilities.TokenUtil.TransactionFailedException;
import com.example.onlinebankingappproject.api.ApiPostTransactionService;
import com.example.onlinebankingappproject.model.response_models.TransactionResponseModel;

import java.util.concurrent.CompletableFuture;

public class TransferActivity extends BaseActivity {

    private EditText targetAccountEditText, amountEditText;
    private Button transferButton;
    private ApiPostTransactionService apiPostTransactionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        targetAccountEditText = findViewById(R.id.targetAccountEditText);
        amountEditText = findViewById(R.id.amountEditText);
        transferButton = findViewById(R.id.transferButton);

        apiPostTransactionService = new ApiPostTransactionService(this);

        transferButton.setOnClickListener(new View.OnClickListener() {
            int accountId = getAccountId(); // Hesap ID'sini alacak bir metodunuzun olduğunu varsayalım

            @Override
            public void onClick(View v) {
                performTransfer(accountId);
            }
        });
    }

    private int getAccountId() {
        Intent intent = getIntent();
        return intent.getIntExtra("sourceAccount", -1); // -1, geçerli bir account_id alınamadığında varsayılan değer
    }

    private void performTransfer(int sourceAccount) {
        String targetAccount = targetAccountEditText.getText().toString().trim();
        String amount = amountEditText.getText().toString().trim();

        if (targetAccount.isEmpty() || amount.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        CompletableFuture<TransactionResponseModel> transferFuture = apiPostTransactionService.transferTransaction(String.valueOf(sourceAccount), targetAccount, amount);

        transferFuture
                .thenAccept(responseData -> {
                    // İşlem başarılı ise buraya gelir
                    Toast.makeText(TransferActivity.this, "Para transfer işlemi başarı ile gerçekleşti", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, AccountOperationsActivity.class);
                    finish();
                    startActivity(intent);
                })
                .exceptionally(exception -> {
                    // İşlem başarısız olduğunda buraya gelir
                    exception.printStackTrace();
                    Toast.makeText(TransferActivity.this, "Para transfer işlemi başarısız oldu.", Toast.LENGTH_SHORT).show();
                    return null;
                });
    }
}
