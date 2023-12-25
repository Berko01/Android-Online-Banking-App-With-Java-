package com.example.onlinebankingappproject.view;// DashboardActivity.java

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.adapters.DashboardAdapter;
import com.example.onlinebankingappproject.api.ApiGetTransactionService;

public class DashboardActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private DashboardAdapter dashboardAdapter;
    private TextView totalUserBalance;
    private ApiGetTransactionService apiGetTransactionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview);
        dashboardAdapter = new DashboardAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(dashboardAdapter);
        totalUserBalance= findViewById(R.id.totalUserBalance);
        apiGetTransactionService = new ApiGetTransactionService(this);
        getDashboardData();
    }

    private void getDashboardData() {
        apiGetTransactionService.getDashboardAsync().thenAccept(responseData -> {
            // Verileri adaptöre set et
            totalUserBalance.setText(responseData.getTotalBalance().toString());
            dashboardAdapter.setData(responseData.getUserAccounts());
        }).exceptionally(ex -> {
            // Hata durumunu ele al
            System.err.println("Error: " + ex.getMessage());
            return null;
        });
    }
}
