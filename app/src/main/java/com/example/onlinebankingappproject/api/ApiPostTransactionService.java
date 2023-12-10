package com.example.onlinebankingappproject.api;

import com.example.onlinebankingappproject.model.RequestModels.CreateAccountRequestModel;
import com.example.onlinebankingappproject.model.RequestModels.LoginRequestModel;
import com.example.onlinebankingappproject.model.ResponseModels.AccessTokenModel;
import com.example.onlinebankingappproject.model.ResponseModels.AccountModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiPostTransactionService {

    public void createAccount(String account_name, String account_type) {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Login Request modeli oluştur
        CreateAccountRequestModel createAccountRequestModel = new CreateAccountRequestModel(account_name, account_type);
        // API'ye POST isteği gönder
        Call<AccountModel> call = apiService.createAccount(createAccountRequestModel);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<AccountModel>() {
            @Override
            public void onResponse(Call<AccountModel> call, Response<AccountModel> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    AccountModel responseData = response.body();
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.getAccount_name() + " " + responseData.getAccount_type());
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
            public void onFailure(Call<AccountModel> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
            }
        });
    }
}
