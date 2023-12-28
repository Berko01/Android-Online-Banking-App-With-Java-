package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.model.base_models.AccountModel;
import com.example.onlinebankingappproject.model.base_models.SerializableAccountModel;

public class AccountOperationsActivity extends BaseActivity {

    private TextView accountNumberTextView, accountNameTextView, accountTypeTextView, balanceTextView,accountIdTextView;
    private CardView accountInfoCardView;
    private AccountModel selectedAccount;
    private Button depositButton, transferButton, withdrawButton, paymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_operations);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // XML bileşenlerine erişim sağlama
        accountInfoCardView = findViewById(R.id.accountInfoCardView);
        accountNumberTextView = findViewById(R.id.accountNumberTextView);
        accountNameTextView = findViewById(R.id.accountNameTextView);
        accountTypeTextView = findViewById(R.id.accountTypeTextView);
        balanceTextView = findViewById(R.id.balanceTextView);
        accountIdTextView=findViewById(R.id.accountIdTextView);

        depositButton = findViewById(R.id.depositButton);
        transferButton = findViewById(R.id.transferButton);
        withdrawButton = findViewById(R.id.withdrawButton);
        paymentButton = findViewById(R.id.paymentButton);

        // Seçilen hesap bilgilerini alma
        Intent intent = getIntent();
        SerializableAccountModel serializableAccountModel = (SerializableAccountModel) intent.getSerializableExtra("selectedAccount");
        AccountModel selectedAccount = serializableAccountModel.getAccountModel();

        // Hesap bilgilerini gösterme
        showAccountInfo(selectedAccount.getAccount_number(), selectedAccount.getAccount_name(), selectedAccount.getAccount_type(), selectedAccount.getBalance().toString(),String.valueOf(selectedAccount.getAccount_id()));

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

            }
        });

        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent depositIntent = new Intent(AccountOperationsActivity.this, WithdrawActivity.class);
                depositIntent.putExtra("account_id", selectedAccount.getAccount_id());
                startActivity(depositIntent);

            }
        });

        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent depositIntent = new Intent(AccountOperationsActivity.this, PaymentActivity.class);
                depositIntent.putExtra("account_id", selectedAccount.getAccount_id());
                startActivity(depositIntent);
            }
        });
    }

    // Hesap bilgilerini gösteren yardımcı metot
    private void showAccountInfo(String accountNumber, String accountName, String accountType, String balance,String id) {
        accountNumberTextView.setText(accountNumber);
        accountNameTextView.setText(accountName);
        accountTypeTextView.setText(accountType);
        balanceTextView.setText(balance);
        accountIdTextView.setText(id);
    }
}
