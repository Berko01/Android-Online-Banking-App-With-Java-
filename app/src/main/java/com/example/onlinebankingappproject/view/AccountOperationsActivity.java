package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.model.ResponseModels.AccountModel;
import com.example.onlinebankingappproject.model.ResponseModels.SerializableAccountModel;

public class AccountOperationsActivity extends AppCompatActivity {

    private TextView accountNumberTextView, accountNameTextView, accountTypeTextView, balanceTextView;
    private CardView accountInfoCardView;
    private AccountModel selectedAccount;
    private Button depositButton, transferButton, withdrawButton, paymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_operations);

        // XML bileşenlerine erişim sağlama
        accountInfoCardView = findViewById(R.id.accountInfoCardView);
        accountNumberTextView = findViewById(R.id.accountNumberTextView);
        accountNameTextView = findViewById(R.id.accountNameTextView);
        accountTypeTextView = findViewById(R.id.accountTypeTextView);
        balanceTextView = findViewById(R.id.balanceTextView);

        depositButton = findViewById(R.id.depositButton);
        transferButton = findViewById(R.id.transferButton);
        withdrawButton = findViewById(R.id.withdrawButton);
        paymentButton = findViewById(R.id.paymentButton);

        // Seçilen hesap bilgilerini alma
        Intent intent = getIntent();
        SerializableAccountModel serializableAccountModel = (SerializableAccountModel) intent.getSerializableExtra("selectedAccount");
        AccountModel selectedAccount = serializableAccountModel.getAccountModel();

        // Hesap bilgilerini gösterme
        showAccountInfo(selectedAccount.getAccount_number(), selectedAccount.getAccount_name(), selectedAccount.getAccount_type(), selectedAccount.getBalance().toString());

        // İşlem butonlarına tıklama olaylarını ayarlama
        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent depositIntent = new Intent(AccountOperationsActivity.this, DepositActivity.class);
                depositIntent.putExtra("account_id", selectedAccount.getAccount_id());
                startActivity(depositIntent);
            }
        });
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent depositIntent = new Intent(AccountOperationsActivity.this, TransferActivity.class);
                depositIntent.putExtra("sourceAccount", selectedAccount.getAccount_id());
                startActivity(depositIntent);
                finish();
            }
        });

        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent depositIntent = new Intent(AccountOperationsActivity.this, WithdrawActivity.class);
                depositIntent.putExtra("account_id", selectedAccount.getAccount_id());
                startActivity(depositIntent);
                finish();
            }
        });

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent depositIntent = new Intent(AccountOperationsActivity.this, PaymentActivity.class);
                depositIntent.putExtra("account_id", selectedAccount.getAccount_id());
                startActivity(depositIntent);
                finish();
            }
        });
    }

    // Hesap bilgilerini gösteren yardımcı metot
    private void showAccountInfo(String accountNumber, String accountName, String accountType, String balance) {
        accountNumberTextView.setText("Hesap Numarası: " + accountNumber);
        accountNameTextView.setText("Hesap Adı: " + accountName);
        accountTypeTextView.setText("Hesap Türü: " + accountType);
        balanceTextView.setText("Bakiye: " + balance);
        finish();
    }
}
