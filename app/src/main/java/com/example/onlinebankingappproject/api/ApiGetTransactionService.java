package com.example.onlinebankingappproject.api;

import android.content.Context;

import com.example.onlinebankingappproject.Utilities.TokenUtil.ApiAuthException;
import com.example.onlinebankingappproject.Utilities.TokenUtil.TransactionFailedException;
import com.example.onlinebankingappproject.model.response_models.DashboardResponseModel;
import com.example.onlinebankingappproject.model.response_models.PaymentHistoryResponseModel;
import com.example.onlinebankingappproject.Utilities.TokenUtil.LocalStorageManager;
import com.example.onlinebankingappproject.model.response_models.TransactionHistoryResponseModel;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiGetTransactionService {
    private Context context;
    private LocalStorageManager localStorageManager;

    public ApiGetTransactionService(Context context) {
        this.context = context;
        this.localStorageManager = new LocalStorageManager(context);

    }

    public CompletableFuture<DashboardResponseModel> getDashboardAsync() {
        CompletableFuture<DashboardResponseModel> future = new CompletableFuture<>();

        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Access token'ı SharedPreferences veya başka bir kaynaktan al
        String accessToken = localStorageManager.getAccessToken();

        // API'ye POST isteği gönder
        Call<DashboardResponseModel> call = apiService.getDashboard("Bearer: " + accessToken);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<DashboardResponseModel>() {
            @Override
            public void onResponse(Call<DashboardResponseModel> call, Response<DashboardResponseModel> response) {
                if (response.isSuccessful()) {
                    DashboardResponseModel responseData = response.body();
                    future.complete(responseData);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                        future.completeExceptionally(new RuntimeException("Error Response: " + errorBody));
                    } catch (Exception e) {
                        e.printStackTrace();
                        future.completeExceptionally(e);
                        throw new TransactionFailedException("Transaction is failed.");


                    }
                }
            }

            @Override
            public void onFailure(Call<DashboardResponseModel> call, Throwable t) {
                System.err.println("Request Failure: " + t.getMessage());
                future.completeExceptionally(t);
                throw new TransactionFailedException("Transaction is failed.");
            }
        });

        return future;
    }

    public CompletableFuture<PaymentHistoryResponseModel> getPaymentHistory() {
        CompletableFuture<PaymentHistoryResponseModel> future = new CompletableFuture<>();
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Access token'ı SharedPreferences veya başka bir kaynaktan al
        String accessToken = localStorageManager.getAccessToken();


        // API'ye POST isteği gönder
        Call<PaymentHistoryResponseModel> call = apiService.getPaymentHistory("Bearer " + accessToken);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<PaymentHistoryResponseModel>() {
            @Override
            public void onResponse(Call<PaymentHistoryResponseModel> call, Response<PaymentHistoryResponseModel> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    PaymentHistoryResponseModel responseData = response.body();
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.getPaymentHistory());
                    future.complete(responseData);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                        future.completeExceptionally(new RuntimeException("Error Response: " + errorBody));
                    } catch (Exception e) {
                        e.printStackTrace();
                        future.completeExceptionally(e);
                        throw new TransactionFailedException("Transaction is failed.");
                    }
                }
            }

            @Override
            public void onFailure(Call<PaymentHistoryResponseModel> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
                future.completeExceptionally(t);
                throw new TransactionFailedException("Transaction is failed.");
            }
        });

        return future;
    }

    public CompletableFuture<TransactionHistoryResponseModel> getTransactionHistory() {
        CompletableFuture<TransactionHistoryResponseModel> future = new CompletableFuture<>();
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Access token'ı SharedPreferences veya başka bir kaynaktan al
        String accessToken = localStorageManager.getAccessToken();


        // API'ye POST isteği gönder
        Call<TransactionHistoryResponseModel> call = apiService.getTransactionHistory("Bearer " + accessToken);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<TransactionHistoryResponseModel>() {
            @Override
            public void onResponse(Call<TransactionHistoryResponseModel> call, Response<TransactionHistoryResponseModel> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    TransactionHistoryResponseModel responseData = response.body();
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.getTransactionHistory());
                    future.complete(responseData);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                        future.completeExceptionally(e);
                        throw new TransactionFailedException("Transaction is failed.");
                    }
                }
            }

            @Override
            public void onFailure(Call<TransactionHistoryResponseModel> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
                future.completeExceptionally(t);
                throw new TransactionFailedException("Transaction is failed.");
            }
        });

        return future;
    }
}