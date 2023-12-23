import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinebankingappproject.api.ApiGetTransactionService;
import com.example.onlinebankingappproject.model.ResponseModels.DashboardResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    private TextView totalAmount;
    private RecyclerView recyclerView;
    private AccountListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        totalAmount = findViewById(R.id.totalAmount);
        recyclerView = findViewById(R.id.recyclerView);

        // API'den toplam miktar bilgisini ve banka hesaplarını al
        getDashboardData();
    }

    private void getDashboardData() {
        ApiGetTransactionService apiGetTransactionService = new ApiGetTransactionService();
        apiGetTransactionService.getDashboard(new Callback<DashboardResponseModel>() {
            @Override
            public void onResponse(Call<DashboardResponseModel> call, Response<DashboardResponseModel> response) {
                if (response.isSuccessful()) {
                    DashboardResponseModel responseData = response.body();

                    // Toplam miktarı set et
                    totalAmount.setText("Toplam Miktar: " + responseData.getTotalBalance() + " TL");

                    // Banka hesaplarını RecyclerView'da göstermek için adapter oluştur
                    adapter = new AccountListAdapter(responseData.getUserAccounts());
                    recyclerView.setAdapter(adapter);

                    // RecyclerView'ın düzenini ayarla
                    recyclerView.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));
                } else {
                    // Hata durumunda
                    // ...
                }
            }

            @Override
            public void onFailure(Call<DashboardResponseModel> call, Throwable t) {
                // İstek başarısız olduğunda
                // ...
            }
        });
    }
}
