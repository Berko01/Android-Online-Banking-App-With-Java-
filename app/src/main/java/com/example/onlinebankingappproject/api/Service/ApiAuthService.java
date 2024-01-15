package com.example.onlinebankingappproject.api.Service;

import android.content.Context;

import com.example.onlinebankingappproject.Utilities.token_util.error_util.ApiAuthException;
import com.example.onlinebankingappproject.api.Client.ApiClient;
import com.example.onlinebankingappproject.api.ApiServiceInterface;
import com.example.onlinebankingappproject.model.base_models.AccessTokenModel;
import com.example.onlinebankingappproject.model.request_models.LoginRequestModel;
import com.example.onlinebankingappproject.model.request_models.RegisterRequestModel;
import com.example.onlinebankingappproject.model.response_models.RegisterResponseModel;
import com.example.onlinebankingappproject.Utilities.token_util.LocalStorageManager;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiAuthService {

    private final Context context;
    private final LocalStorageManager localStorageManager;

    // Constructor, bağlam ve yerel depolama yöneticisini başlatır
    public ApiAuthService(Context context) {
        this.context = context;
        this.localStorageManager = new LocalStorageManager(context);
    }

    // Kullanıcı girişi yapmak için asenkron bir CompletableFuture döndüren metot
    public CompletableFuture<AccessTokenModel> login(String email, String password) {
        CompletableFuture<AccessTokenModel> future = new CompletableFuture<>();

        Retrofit retrofit = ApiClient.getClient();
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        LoginRequestModel loginRequestModel = new LoginRequestModel(email, password);
        Call<AccessTokenModel> call = apiService.login(loginRequestModel);

        call.enqueue(new Callback<AccessTokenModel>() {
            @Override
            public void onResponse(Call<AccessTokenModel> call, Response<AccessTokenModel> response) {
                if (response.isSuccessful()) {
                    AccessTokenModel responseData = response.body();
                    System.out.println("Response Data: " + responseData.getAccessToken() + " " + responseData.getMessage());
                    localStorageManager.saveAccessToken(responseData.getAccessToken());

                    future.complete(responseData);

                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        localStorageManager.saveAccessToken(null);
                        System.err.println("Error Response: " + errorBody);
                        throw new ApiAuthException("Login işlemi başarısız oldu.");
                    } catch (Exception e) {
                        future.completeExceptionally(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<AccessTokenModel> call, Throwable t) {
                System.err.println("Request Failure: " + t.getMessage());
                future.completeExceptionally(new ApiAuthException("Login işlemi başarısız oldu."));
            }
        });

        return future;
    }

    // Kullanıcı kaydı yapmak için asenkron bir CompletableFuture döndüren metot
    public CompletableFuture<RegisterResponseModel> register(String first_name, String last_name, String email, String password) {
        CompletableFuture<RegisterResponseModel> future = new CompletableFuture<>();

        Retrofit retrofit = ApiClient.getClient();
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);
        RegisterRequestModel registerRequestModel = new RegisterRequestModel(first_name, last_name, email, password);
        Call<RegisterResponseModel> call = apiService.register(registerRequestModel);

        call.enqueue(new Callback<RegisterResponseModel>() {
            @Override
            public void onResponse(Call<RegisterResponseModel> call, Response<RegisterResponseModel> response) {
                if (response.isSuccessful()) {
                    RegisterResponseModel responseData = response.body();
                    System.out.println("Response Data: " + responseData.getMessage() + " " + responseData.getUser());
                    future.complete(responseData);
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                        throw new ApiAuthException("Kayıt islemi başarısız oldu.");
                    } catch (Exception e) {
                        future.completeExceptionally(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
                System.err.println("Request Failure: " + t.getMessage());
                future.completeExceptionally(t);
            }
        });

        return future;
    }

    // Kullanıcı çıkışı yapmak için asenkron bir CompletableFuture döndüren metot
    public CompletableFuture<Void> logout() {
        CompletableFuture<Void> future = new CompletableFuture<>();

        Retrofit retrofit = ApiClient.getClient();
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);
        String accessToken = localStorageManager.getAccessToken();
        Call<Void> call = apiService.logout("Bearer: " + accessToken);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    System.out.println("Logout Successfull.");
                    localStorageManager.removeAccessToken();
                    future.complete(null);
                    throw new ApiAuthException("Logout islemi basarisiz oldu.");
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        System.err.println("Error Response: " + errorBody);
                    } catch (Exception e) {
                        future.completeExceptionally(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.err.println("Request Failure: " + t.getMessage());
                future.completeExceptionally(t);
            }
        });

        return future;
    }
}
