package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.Service.ApiPostTransactionService;
import com.example.onlinebankingappproject.model.response_models.TransactionResponseModel;

import java.util.concurrent.CompletableFuture;

public class PaymentActivity extends BaseActivity {

    private EditText beneficiaryEditText, accountNumberEditText, referenceEditText, paymentAmountEditText;
    private Button paymentButton;
    ApiPostTransactionService apiPostTransactionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        beneficiaryEditText = findViewById(R.id.beneficiaryEditText);
        accountNumberEditText = findViewById(R.id.accountNumberEditText);
        referenceEditText = findViewById(R.id.referenceEditText);
        paymentAmountEditText = findViewById(R.id.paymentAmountEditText);
        paymentButton = findViewById(R.id.paymentButton);
        apiPostTransactionService = new ApiPostTransactionService(this);
        int accountId = getAccountId(); // Hesap ID'sini alacak bir metodunuzun olduğunu varsayalım

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performPayment(accountId);
            }
        });
    }

    private int getAccountId() {
        Intent intent = getIntent();
        return intent.getIntExtra("account_id", -1); // -1, geçerli bir account_id alınamadığında varsayılan değer
    }

    private void performPayment(int accountId) {
        String beneficiary = beneficiaryEditText.getText().toString().trim();
        String accountNumber = accountNumberEditText.getText().toString().trim();
        String reference = referenceEditText.getText().toString().trim();
        String paymentAmount = paymentAmountEditText.getText().toString().trim();

        if (beneficiary.isEmpty() || accountNumber.isEmpty() || reference.isEmpty() || paymentAmount.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        CompletableFuture<TransactionResponseModel> future = apiPostTransactionService.paymentTransaction(beneficiary, accountNumber, String.valueOf(accountId), reference, paymentAmount);

        future.whenComplete((result, exception) -> {
            runOnUiThread(() -> {
                if (exception == null) {
                    // İşlem başarılı ise
                    Toast.makeText(PaymentActivity.this, "Ödeme işlemi başarı ile gerçekleşti", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PaymentActivity.this, AccountOperationsActivity.class);
                    finish();
                    startActivity(intent);
                } else {
                    // İşlem başarısız ise
                    exception.printStackTrace();
                    Toast.makeText(PaymentActivity.this, "Ödeme işlemi başarısız oldu.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
