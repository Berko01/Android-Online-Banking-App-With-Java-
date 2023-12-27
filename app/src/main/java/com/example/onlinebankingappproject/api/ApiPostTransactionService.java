package com.example.onlinebankingappproject.api;

import android.content.Context;

import com.example.onlinebankingappproject.model.request_models.CreateAccountRequestModel;
import com.example.onlinebankingappproject.model.request_models.DepositRequestModel;
import com.example.onlinebankingappproject.model.request_models.PaymentRequestModel;
import com.example.onlinebankingappproject.model.request_models.TransferRequestModel;
import com.example.onlinebankingappproject.model.request_models.WithdrawRequestModel;
import com.example.onlinebankingappproject.model.base_models.AccountModel;
import com.example.onlinebankingappproject.model.response_models.TransactionResponseModel;
import com.example.onlinebankingappproject.Utilities.TokenUtil.LocalStorageManager;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiPostTransactionService {
    private Context context;
    private LocalStorageManager localStorageManager;

    public ApiPostTransactionService(Context context){
        this.context = context;
        this.localStorageManager = new LocalStorageManager(context);
    }
    public CompletableFuture<AccountModel> createAccount(String account_name, String account_type) {
        CompletableFuture<AccountModel> future = new CompletableFuture<>();
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Access token'ı SharedPreferences veya başka bir kaynaktan al
        String accessToken = localStorageManager.getAccessToken();

        // Login Request modeli oluştur
        CreateAccountRequestModel createAccountRequestModel = new CreateAccountRequestModel(account_name, account_type);
        // API'ye POST isteği gönder
        Call<AccountModel> call = apiService.createAccount("Bearer " + accessToken, createAccountRequestModel);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    AccountModel responseData = response.body();
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.getAccount_name() + " " + responseData.getAccount_type());
                    future.complete(responseData);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                        future.completeExceptionally(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
                future.completeExceptionally(t);
            }
        });

        return future;
    }


    public void depositTransaction(String deposit_amount, int account_id) {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Access token'ı SharedPreferences veya başka bir kaynaktan al
        String accessToken = localStorageManager.getAccessToken();

        // Login Request modeli oluştur
        DepositRequestModel depositRequestModel = new DepositRequestModel(deposit_amount, account_id);
        // API'ye POST isteği gönder
        Call<TransactionResponseModel> call = apiService.depositTransaction("Bearer " + accessToken, depositRequestModel);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<TransactionResponseModel>() {
            @Override
            public void onResponse(Call<TransactionResponseModel> call, Response<TransactionResponseModel> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    TransactionResponseModel responseData = response.body();
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.getUserAccounts());
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
            public void onFailure(Call<TransactionResponseModel> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
            }
        });
    }

    public void transferTransaction(String sourceAccount, String targetAccount, String amount) {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Access token'ı SharedPreferences veya başka bir kaynaktan al
        String accessToken = localStorageManager.getAccessToken();
        System.out.println(sourceAccount + " " + targetAccount + " " + amount);
        // Login Request modeli oluştur
        TransferRequestModel transferRequestModel = new TransferRequestModel(sourceAccount, targetAccount,amount);
        // API'ye POST isteği gönder
        Call<TransactionResponseModel> call = apiService.transferTransaction("Bearer " + accessToken, transferRequestModel);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<TransactionResponseModel>() {
            @Override
            public void onResponse(Call<TransactionResponseModel> call, Response<TransactionResponseModel> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    TransactionResponseModel responseData = response.body();
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.getUserAccounts());
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
            public void onFailure(Call<TransactionResponseModel> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
            }
        });
    }


    public void withdrawTransaction(String withdrawal_amount, String account_id) {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Access token'ı SharedPreferences veya başka bir kaynaktan al
        String accessToken = localStorageManager.getAccessToken();

        // Login Request modeli oluştur
        WithdrawRequestModel withdrawRequestModel = new WithdrawRequestModel(withdrawal_amount, account_id);
        // API'ye POST isteği gönder
        Call<TransactionResponseModel> call = apiService.withdrawTransaction("Bearer " + accessToken, withdrawRequestModel);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<TransactionResponseModel>() {
            @Override
            public void onResponse(Call<TransactionResponseModel> call, Response<TransactionResponseModel> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    TransactionResponseModel responseData = response.body();
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.getUserAccounts());
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
            public void onFailure(Call<TransactionResponseModel> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
            }
        });
    }


    public void paymentTransaction(String beneficiary, String account_number,String account_id,
                                   String reference, String payment_amount) {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Access token'ı SharedPreferences veya başka bir kaynaktan al
        String accessToken = localStorageManager.getAccessToken();

        // Login Request modeli oluştur
        PaymentRequestModel paymentRequestModel = new PaymentRequestModel(beneficiary, account_number,account_id,reference,payment_amount);
        // API'ye POST isteği gönder
        Call<TransactionResponseModel> call = apiService.paymentTransaction("Bearer " + accessToken, paymentRequestModel);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<TransactionResponseModel>() {
            @Override
            public void onResponse(Call<TransactionResponseModel> call, Response<TransactionResponseModel> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    TransactionResponseModel responseData = response.body();
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.getUserAccounts());
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
            public void onFailure(Call<TransactionResponseModel> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
            }
        });
    }
}