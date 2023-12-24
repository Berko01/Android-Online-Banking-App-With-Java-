package com.example.onlinebankingappproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.api.ApiPostTransactionService;
import com.example.onlinebankingappproject.model.RequestModels.PaymentRequestModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends BaseActivity {

    private EditText beneficiaryEditText, accountNumberEditText, referenceEditText, paymentAmountEditText;
    private Button paymentButton;
    ApiPostTransactionService apiPostTransactionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

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
                makePayment(accountId);
            }
        });
    }
    private int getAccountId() {
        Intent intent = getIntent();
        return intent.getIntExtra("account_id", -1); // -1, geçerli bir account_id alınamadığında varsayılan değer
    }
    private void makePayment(int accountId) {
        String beneficiary = beneficiaryEditText.getText().toString().trim();
        String accountNumber = accountNumberEditText.getText().toString().trim();
        String reference = referenceEditText.getText().toString().trim();
        String paymentAmount = paymentAmountEditText.getText().toString().trim();

        if (beneficiary.isEmpty() || accountNumber.isEmpty() || reference.isEmpty() || paymentAmount.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        apiPostTransactionService.paymentTransaction(beneficiary,accountNumber,String.valueOf(accountId),reference,paymentAmount);
        Toast.makeText(PaymentActivity.this, "Ödeme işlemi başarı ile gerçekleşti", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}
