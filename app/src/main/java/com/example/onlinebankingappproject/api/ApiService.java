package com.example.onlinebankingappproject.api;

import com.example.onlinebankingappproject.model.AccessTokenModel;
import com.example.onlinebankingappproject.model.LoginRequestModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiService {

    public void login(String email, String password) {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);


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
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.getAccessToken() + " " + responseData.getMessage());
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
            public void onFailure(Call<AccessTokenModel> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
            }
        });
    }
}
