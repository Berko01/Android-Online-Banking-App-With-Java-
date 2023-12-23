package com.example.onlinebankingappproject.api;
import android.util.Log;
import android.content.Context;

import com.example.onlinebankingappproject.Utilities.TokenUtil.AccessTokenManager;
import com.example.onlinebankingappproject.Utilities.TokenUtil.TokenManager;
import com.example.onlinebankingappproject.model.RequestModels.RegisterRequestModel;
import com.example.onlinebankingappproject.model.ResponseModels.AccessTokenModel;
import com.example.onlinebankingappproject.model.RequestModels.LoginRequestModel;
import com.example.onlinebankingappproject.model.ResponseModels.RegisterResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiAuthService {
    AccessTokenManager tokenManager;
    public ApiAuthService() {
        tokenManager = AccessTokenManager.getInstance();
    }

    public void login(String email, String password) {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();
        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Login Request modeli oluştur
        LoginRequestModel loginRequestModel = new LoginRequestModel(email, password);
        // API'ye POST isteği gönder
        Call<AccessTokenModel> call = apiService.login(loginRequestModel);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<AccessTokenModel>() {
            @Override
            public void onResponse(Call<AccessTokenModel> call, Response<AccessTokenModel> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    AccessTokenModel responseData = response.body();
                    tokenManager.setAccessToken(responseData.getAccessToken());
                    // response verilerini kullan
                    Log.d("ApiAuthService", "Response Data: " + tokenManager.getAccessToken() + " " + responseData.getMessage());
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("ApiAuthService", "Error Response: " + errorBody);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AccessTokenModel> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                Log.e("ApiAuthService", "Request Failure: " + t.getMessage());
            }
        });
    }


    public void register(String first_name, String last_name, String email, String password) {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Login Request modeli oluştur
        RegisterRequestModel registerRequestModel = new RegisterRequestModel(first_name, last_name, email, password);
        // API'ye POST isteği gönder
        Call<RegisterResponseModel> call = apiService.register(registerRequestModel);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<RegisterResponseModel>() {
            @Override
            public void onResponse(Call<RegisterResponseModel> call, Response<RegisterResponseModel> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    RegisterResponseModel responseData = response.body();
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.getMessage() + " " + responseData.getUser());
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
            public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
            }
        });
    }
}
