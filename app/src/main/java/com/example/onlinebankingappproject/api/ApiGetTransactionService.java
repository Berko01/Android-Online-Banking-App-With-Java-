package com.example.onlinebankingappproject.api;

import android.content.Context;

import com.example.onlinebankingappproject.model.ResponseModels.DashboardResponseModel;
import com.example.onlinebankingappproject.model.ResponseModels.PaymentHistoryModel;
import com.example.onlinebankingappproject.model.ResponseModels.TransactionHistoryModel;
import com.example.onlinebankingappproject.Utilities.TokenUtil.LocalStorageManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiGetTransactionService {
    private Context context;
    private LocalStorageManager localStorageManager;

    public ApiGetTransactionService(Context context){
        this.context = context;
        this.localStorageManager = new LocalStorageManager(context);

    }

    public void getDashboard(String account_name, String account_type) {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Access token'ı SharedPreferences veya başka bir kaynaktan al
        String accessToken = localStorageManager.getAccessToken();


        // API'ye POST isteği gönder
        Call<DashboardResponseModel> call = apiService.getDashboard("Bearer " + accessToken);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<DashboardResponseModel>() {
            @Override
            public void onResponse(Call<DashboardResponseModel> call, Response<DashboardResponseModel> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    DashboardResponseModel responseData = response.body();
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.getUserAccounts() + " " + responseData.getTotalBalance());
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DashboardResponseModel> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
            }
        });
    }


    public void getPaymentHistory(String account_name, String account_type) {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Access token'ı SharedPreferences veya başka bir kaynaktan al
        String accessToken = localStorageManager.getAccessToken();


        // API'ye POST isteği gönder
        Call<List<PaymentHistoryModel>> call = apiService.getPaymentHistory("Bearer " + accessToken);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<List<PaymentHistoryModel>>() {
            @Override
            public void onResponse(Call<List<PaymentHistoryModel>> call, Response<List<PaymentHistoryModel>> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    List<PaymentHistoryModel> responseData = response.body();
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.get(0));
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PaymentHistoryModel>> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
            }
        });
    }


    public void getTransactionHistory(String account_name, String account_type) {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Access token'ı SharedPreferences veya başka bir kaynaktan al
        String accessToken = localStorageManager.getAccessToken();


        // API'ye POST isteği gönder
        Call<List<TransactionHistoryModel>> call = apiService.getTransactionHistory("Bearer " + accessToken);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<List<TransactionHistoryModel>>() {
            @Override
            public void onResponse(Call<List<TransactionHistoryModel>> call, Response<List<TransactionHistoryModel>> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    List<TransactionHistoryModel> responseData = response.body();
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.get(0));
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TransactionHistoryModel>> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
            }
        });
    }




}