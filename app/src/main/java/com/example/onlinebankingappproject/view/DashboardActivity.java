package com.example.onlinebankingappproject.view;// DashboardActivity.java

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlinebankingappproject.R;
import com.example.onlinebankingappproject.adapters.DashboardAdapter;
import com.example.onlinebankingappproject.api.ApiGetTransactionService;
import com.example.onlinebankingappproject.model.ResponseModels.DashboardResponseModel;
import com.example.onlinebankingappproject.view.BaseActivity;

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
        dashboardAdapter = new DashboardAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(dashboardAdapter);
        totalUserBalance= findViewById(R.id.totalUserBalance);
        Button startProcessButton = findViewById(R.id.startProcessButton);
        apiGetTransactionService = new ApiGetTransactionService(this);

        // getDashboardData() fonksiyonunu onCreate'te çağır
        getDashboardData();

        // startProcessButton onClickListener'ını kaldır, çünkü otomatik olarak çağrıldı
        // startProcessButton.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View view) {
        //         // Butona tıklandığında yapılacak işlemleri burada tanımla
        //         getDashboardData();
        //     }
        // });
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
