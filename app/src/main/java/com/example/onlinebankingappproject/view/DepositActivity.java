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
import com.example.onlinebankingappproject.api.ApiPostTransactionService;
import com.example.onlinebankingappproject.model.response_models.TransactionResponseModel;

import java.util.concurrent.CompletableFuture;

public class DepositActivity extends BaseActivity {

    private EditText amountEditText;
    private Button depositButton;
    ApiPostTransactionService apiPostTransactionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        amountEditText = findViewById(R.id.amountEditText);
        depositButton = findViewById(R.id.depositButton);
        apiPostTransactionService = new ApiPostTransactionService(this);

        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText'ten miktarı al
                String depositAmount = amountEditText.getText().toString();

                // Hesap ID'sini al (Bu değeri isteğe bağlı olarak kullanabilirsiniz)
                int accountId = getAccountId(); // Hesap ID'sini alacak bir metodunuzun olduğunu varsayalım

                // Para yatırma işlemi
                performDeposit(depositAmount, accountId);
            }
        });
    }

    private int getAccountId() {
        Intent intent = getIntent();
        return intent.getIntExtra("account_id", -1); // -1, geçerli bir account_id alınamadığında varsayılan değer
    }

    private void performDeposit(String depositAmount, int accountId) {
        CompletableFuture<TransactionResponseModel> future = apiPostTransactionService.depositTransaction(depositAmount, accountId);

        future.whenComplete((result, exception) -> {
            runOnUiThread(() -> {
                if (exception == null) {
                    // İşlem başarılı ise
                    Toast.makeText(DepositActivity.this, "Para yatırma işlemi başarı ile gerçekleşti", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DepositActivity.this, AccountOperationsActivity.class);
                    finish();
                    startActivity(intent);
                } else {
                    // İşlem başarısız ise
                    exception.printStackTrace();
                    Toast.makeText(DepositActivity.this, "Para yatırma işlemi başarısız oldu.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
