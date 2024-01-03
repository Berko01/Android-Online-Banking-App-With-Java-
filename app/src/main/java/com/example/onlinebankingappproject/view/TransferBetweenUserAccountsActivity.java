package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.adapters.SpinnerAdapter;
import com.example.onlinebankingappproject.api.Service.ApiGetTransactionService;
import com.example.onlinebankingappproject.api.Service.ApiPostTransactionService;
import com.example.onlinebankingappproject.model.base_models.AccountModel;
import com.example.onlinebankingappproject.model.response_models.TransactionResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TransferBetweenUserAccountsActivity extends BaseActivity {

    private Spinner accountSpinner;
    private EditText amountEditText;
    private Button transferButton;
    private ApiPostTransactionService apiPostTransactionService;
    private int sourceAccountId;
    private ApiGetTransactionService apiGetTransactionService;
    SpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_between_user_accounts);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        accountSpinner = findViewById(R.id.accountSpinner);
        amountEditText = findViewById(R.id.amountEditText);
        transferButton = findViewById(R.id.transferButton);
        spinnerAdapter = new SpinnerAdapter(this, R.layout.spinner_item, new ArrayList<>()); // Değişiklik burada
        apiPostTransactionService = new ApiPostTransactionService(this);
        // Get the source account ID from the previous activity
        Intent intent = getIntent();
        sourceAccountId = intent.getIntExtra("account_id", -1);
        apiGetTransactionService = new ApiGetTransactionService(this);

        // Populate the spinner with account names
        apiGetTransactionService.getDashboardAsync().thenAccept(responseData -> {
            // Verileri adaptöre set et
            List<AccountModel> accountList = responseData.getUserAccounts();
            spinnerAdapter.setData(accountList); // Değişiklik burada
        }).exceptionally(ex -> {
            // Hata durumunu ele al
            System.err.println("Error: " + ex.getMessage());
            return null;
        });

        accountSpinner.setAdapter(spinnerAdapter);

        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performTransfer();
            }
        });
    }

    private void performTransfer() {
        AccountModel selectedAccount = (AccountModel) accountSpinner.getSelectedItem();
        String amount = amountEditText.getText().toString().trim();

        if (selectedAccount == null || amount.isEmpty()) {
            Toast.makeText(this, "Please select an account and fill in the amount", Toast.LENGTH_SHORT).show();
            return;
        }

        CompletableFuture<TransactionResponseModel> transferFuture = apiPostTransactionService.transferTransaction(
                String.valueOf(sourceAccountId),
                String.valueOf(selectedAccount.getAccount_id()),
                amount
        );

        transferFuture
                .thenAccept(responseData -> {
                    // Transfer successful
                    Toast.makeText(TransferBetweenUserAccountsActivity.this, "Transfer successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, DashboardActivity.class);
                    finish();
                    startActivity(intent);
                })
                .exceptionally(exception -> {
                    // Transfer unsuccessful
                    exception.printStackTrace();
                    Toast.makeText(TransferBetweenUserAccountsActivity.this, "Transfer failed", Toast.LENGTH_SHORT).show();
                    return null;
                });
    }
}
