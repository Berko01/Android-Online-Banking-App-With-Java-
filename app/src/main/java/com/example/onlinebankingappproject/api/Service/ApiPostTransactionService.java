package com.example.onlinebankingappproject.api.Service;
import android.content.Context;

import com.example.onlinebankingappproject.Utilities.token_util.LocalStorageManager;
import com.example.onlinebankingappproject.Utilities.token_util.error_util.TransactionFailedException;
import com.example.onlinebankingappproject.api.Client.ApiClient;
import com.example.onlinebankingappproject.api.ApiServiceInterface;
import com.example.onlinebankingappproject.model.base_models.AccountModel;
import com.example.onlinebankingappproject.model.request_models.CreateAccountRequestModel;
import com.example.onlinebankingappproject.model.request_models.DepositRequestModel;
import com.example.onlinebankingappproject.model.request_models.PaymentRequestModel;
import com.example.onlinebankingappproject.model.request_models.TransferRequestModel;
import com.example.onlinebankingappproject.model.request_models.WithdrawRequestModel;
import com.example.onlinebankingappproject.model.response_models.TransactionResponseModel;

import java.util.concurrent.CompletableFuture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiPostTransactionService {
    private Context context;
    private LocalStorageManager localStorageManager;

    public ApiPostTransactionService(Context context) {
        this.context = context;
        this.localStorageManager = new LocalStorageManager(context);
    }

    public CompletableFuture<AccountModel> createAccount(String account_name, String account_type) {
        CompletableFuture<AccountModel> future = new CompletableFuture<>();
        Retrofit retrofit = ApiClient.getClient();
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);
        String accessToken = localStorageManager.getAccessToken();
        CreateAccountRequestModel createAccountRequestModel = new CreateAccountRequestModel(account_name, account_type);

        Call<AccountModel> call = apiService.createAccount("Bearer " + accessToken, createAccountRequestModel);

        call.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                if (response.isSuccessful()) {
                    AccountModel responseData = response.body();
                    System.out.println("Response Data: " + responseData.getAccount_name() + " " + responseData.getAccount_type());
                    future.complete(responseData);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                        future.completeExceptionally(new TransactionFailedException("Transaction is failed."));
                    } catch (Exception e) {
                        e.printStackTrace();
                        future.completeExceptionally(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<AccountModel> call, Throwable t) {
                System.err.println("Request Failure: " + t.getMessage());
                future.completeExceptionally(new TransactionFailedException("Transaction is failed."));
            }
        });

        return future;
    }

    public CompletableFuture<TransactionResponseModel> depositTransaction(String deposit_amount, int account_id) {
        CompletableFuture<TransactionResponseModel> future = new CompletableFuture<>();
        Retrofit retrofit = ApiClient.getClient();
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);
        String accessToken = localStorageManager.getAccessToken();
        DepositRequestModel depositRequestModel = new DepositRequestModel(deposit_amount, account_id);

        Call<TransactionResponseModel> call = apiService.depositTransaction("Bearer " + accessToken, depositRequestModel);

        call.enqueue(new Callback<TransactionResponseModel>() {
            @Override
            public void onResponse(Call<TransactionResponseModel> call, Response<TransactionResponseModel> response) {
                if (response.isSuccessful()) {
                    TransactionResponseModel responseData = response.body();
                    System.out.println("Response Data: " + responseData.getUserAccounts());
                    future.complete(responseData);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                        future.completeExceptionally(new TransactionFailedException("Transaction is failed."));
                    } catch (Exception e) {
                        e.printStackTrace();
                        future.completeExceptionally(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<TransactionResponseModel> call, Throwable t) {
                System.err.println("Request Failure: " + t.getMessage());
                future.completeExceptionally(new TransactionFailedException("Transaction is failed."));
            }
        });

        return future;
    }

    public CompletableFuture<TransactionResponseModel> transferTransaction(String sourceAccount, String targetAccount, String amount) {
        CompletableFuture<TransactionResponseModel> future = new CompletableFuture<>();
        Retrofit retrofit = ApiClient.getClient();
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);
        String accessToken = localStorageManager.getAccessToken();
        TransferRequestModel transferRequestModel = new TransferRequestModel(sourceAccount, targetAccount, amount);

        Call<TransactionResponseModel> call = apiService.transferTransaction("Bearer " + accessToken, transferRequestModel);

        call.enqueue(new Callback<TransactionResponseModel>() {
            @Override
            public void onResponse(Call<TransactionResponseModel> call, Response<TransactionResponseModel> response) {
                if (response.isSuccessful()) {
                    TransactionResponseModel responseData = response.body();
                    System.out.println("Response Data: " + responseData.getUserAccounts());
                    future.complete(responseData);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                        future.completeExceptionally(new TransactionFailedException("Transaction is failed."));
                    } catch (Exception e) {
                        e.printStackTrace();
                        future.completeExceptionally(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<TransactionResponseModel> call, Throwable t) {
                System.err.println("Request Failure: " + t.getMessage());
                future.completeExceptionally(new TransactionFailedException("Transaction is failed."));
            }
        });

        return future;
    }

    public CompletableFuture<TransactionResponseModel> withdrawTransaction(String withdrawal_amount, String account_id) {
        CompletableFuture<TransactionResponseModel> future = new CompletableFuture<>();
        Retrofit retrofit = ApiClient.getClient();
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);
        String accessToken = localStorageManager.getAccessToken();
        WithdrawRequestModel withdrawRequestModel = new WithdrawRequestModel(withdrawal_amount, account_id);

        Call<TransactionResponseModel> call = apiService.withdrawTransaction("Bearer " + accessToken, withdrawRequestModel);

        call.enqueue(new Callback<TransactionResponseModel>() {
            @Override
            public void onResponse(Call<TransactionResponseModel> call, Response<TransactionResponseModel> response) {
                if (response.isSuccessful()) {
                    TransactionResponseModel responseData = response.body();
                    System.out.println("Response Data: " + responseData.getUserAccounts());
                    future.complete(responseData);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                        future.completeExceptionally(new TransactionFailedException("Transaction is failed."));
                    } catch (Exception e) {
                        e.printStackTrace();
                        future.completeExceptionally(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<TransactionResponseModel> call, Throwable t) {
                System.err.println("Request Failure: " + t.getMessage());
                future.completeExceptionally(new TransactionFailedException("Transaction is failed."));
            }
        });

        return future;
    }

    public CompletableFuture<TransactionResponseModel> paymentTransaction(String beneficiary, String account_number, String account_id, String reference, String payment_amount) {
        CompletableFuture<TransactionResponseModel> future = new CompletableFuture<>();
        Retrofit retrofit = ApiClient.getClient();
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);
        String accessToken = localStorageManager.getAccessToken();
        PaymentRequestModel paymentRequestModel = new PaymentRequestModel(beneficiary, account_number, account_id, reference, payment_amount);

        Call<TransactionResponseModel> call = apiService.paymentTransaction("Bearer " + accessToken, paymentRequestModel);

        call.enqueue(new Callback<TransactionResponseModel>() {
            @Override
            public void onResponse(Call<TransactionResponseModel> call, Response<TransactionResponseModel> response) {
                if (response.isSuccessful()) {
                    TransactionResponseModel responseData = response.body();
                    System.out.println("Response Data: " + responseData.getUserAccounts());
                    future.complete(responseData);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                        future.completeExceptionally(new TransactionFailedException("Transaction is failed."));
                    } catch (Exception e) {
                        e.printStackTrace();
                        future.completeExceptionally(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<TransactionResponseModel> call, Throwable t) {
                System.err.println("Request Failure: " + t.getMessage());
                future.completeExceptionally(new TransactionFailedException("Transaction is failed."));
            }
        });

        return future;
    }
}
