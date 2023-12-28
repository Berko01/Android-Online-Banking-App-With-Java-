package com.example.onlinebankingappproject.view;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.adapters.PaymentHistoryAdapter;
import com.example.onlinebankingappproject.api.Service.ApiGetTransactionService;

public class PaymentHistoryActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private PaymentHistoryAdapter paymentHistoryAdapter;
    private ApiGetTransactionService apiGetTransactionService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        apiGetTransactionService = new ApiGetTransactionService(this);

        // Adapter'ı bu noktada başlatmalısınız
        paymentHistoryAdapter = new PaymentHistoryAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(paymentHistoryAdapter);

        // getPaymentHistory metodunu çağırabilirsiniz
        getPaymentHistory();
    }

    private void getPaymentHistory() {
        apiGetTransactionService.getPaymentHistory().thenAccept(responseData -> {
            // Verileri adaptöre set et
            paymentHistoryAdapter.setData(responseData.getPaymentHistory());
        }).exceptionally(ex -> {
            // Hata durumunu ele al
            System.err.println("Error: " + ex.getMessage());
            return null;
        });
    }
}
