package com.example.onlinebankingappproject.api;

import com.example.onlinebankingappproject.Utilities.TokenUtil.TokenManager;
import com.example.onlinebankingappproject.model.ResponseModels.DashboardResponseModel;
import com.example.onlinebankingappproject.model.ResponseModels.PaymentHistoryModel;
import com.example.onlinebankingappproject.model.ResponseModels.TransactionHistoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiGetTransactionService {
    public void getDashboard(String account_name, String account_type) {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);


        // API'ye POST isteği gönder
        Call<DashboardResponseModel> call = apiService.getDashboard();

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


        // API'ye POST isteği gönder
        Call<List<PaymentHistoryModel>> call = apiService.getPaymentHistory();

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


        // API'ye POST isteği gönder
        Call<List<TransactionHistoryModel>> call = apiService.getTransactionHistory();

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


    public void logout() {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);


        // API'ye POST isteği gönder
        Call call = apiService.logout();

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<List<TransactionHistoryModel>>() {
            @Override
            public void onResponse(Call call, Response response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    System.out.println("Logged out successfully.");
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
            public void onFailure(Call call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
            }
        });
    }

}
