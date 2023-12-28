package com.example.onlinebankingappproject.view;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.Service.ApiPostTransactionService;
import com.example.onlinebankingappproject.model.response_models.TransactionResponseModel;

import java.util.concurrent.CompletableFuture;

public class WithdrawActivity extends BaseActivity {

    private EditText amountEditText;
    private Button withdrawButton;
    ApiPostTransactionService apiPostTransactionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        amountEditText = findViewById(R.id.amountEditText);
        withdrawButton = findViewById(R.id.withdrawButton);
        apiPostTransactionService = new ApiPostTransactionService(this);

        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText'ten miktarı al
                String withdrawalAmount = amountEditText.getText().toString();

                // Hesap ID'sini al (Bu değeri isteğe bağlı olarak kullanabilirsiniz)
                int accountId = getAccountId(); // Hesap ID'sini alacak bir metodunuzun olduğunu varsayalım

                // Para çekme işlemi
                performWithdrawal(withdrawalAmount, accountId);
            }
        });
    }

    private int getAccountId() {
        Intent intent = getIntent();
        return intent.getIntExtra("account_id", -1); // -1, geçerli bir account_id alınamadığında varsayılan değer
    }

    private void performWithdrawal(String withdrawalAmount, int accountId) {
        CompletableFuture<TransactionResponseModel> future = apiPostTransactionService.withdrawTransaction(withdrawalAmount, String.valueOf(accountId));

        future.whenComplete((result, exception) -> {
            runOnUiThread(() -> {
                if (exception == null) {
                    // İşlem başarılı ise
                    Toast.makeText(WithdrawActivity.this, "Para çekme işlemi başarı ile gerçekleşti", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(WithdrawActivity.this, AccountOperationsActivity.class);
                    finish();
                    startActivity(intent);
                } else {
                    // İşlem başarısız ise
                    exception.printStackTrace();
                    Toast.makeText(WithdrawActivity.this, "Para çekme işlemi başarısız oldu.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
