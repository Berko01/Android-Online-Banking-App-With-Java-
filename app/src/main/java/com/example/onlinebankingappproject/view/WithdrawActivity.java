package com.example.onlinebankingappproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.ApiPostTransactionService;

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
                String depositAmount = amountEditText.getText().toString();

                // Hesap ID'sini al (Bu değeri isteğe bağlı olarak kullanabilirsiniz)
                int accountId = getAccountId(); // Hesap ID'sini alacak bir metodunuzun olduğunu varsayalım

                // Para yatırma işlemi
                withdrawTransaction(depositAmount, accountId);
            }
        });
    }
    private int getAccountId() {
        Intent intent = getIntent();
        return intent.getIntExtra("account_id", -1); // -1, geçerli bir account_id alınamadığında varsayılan değer
    }
    private void withdrawTransaction(String depositAmount, int accountId) {
        apiPostTransactionService.withdrawTransaction(depositAmount,String.valueOf(accountId));
        Toast.makeText(WithdrawActivity.this, "Para çekme işlemi başarı ile gerçekleşti", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}