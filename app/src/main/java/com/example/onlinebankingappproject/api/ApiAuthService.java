package com.example.onlinebankingappproject.api;

import android.content.Context;

import com.example.onlinebankingappproject.Utilities.TokenUtil.ApiAuthException;
import com.example.onlinebankingappproject.model.request_models.RegisterRequestModel;
import com.example.onlinebankingappproject.model.base_models.AccessTokenModel;
import com.example.onlinebankingappproject.model.request_models.LoginRequestModel;
import com.example.onlinebankingappproject.model.response_models.RegisterResponseModel;
import com.example.onlinebankingappproject.Utilities.TokenUtil.LocalStorageManager;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiAuthService {

    private Context context;
    private LocalStorageManager localStorageManager;

    public ApiAuthService(Context context) {
        this.context = context;
        this.localStorageManager = new LocalStorageManager(context);
    }

    public void login(String email, String password) {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
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
                    // response verilerini kullan
                    System.out.println("Response Data: " + responseData.getAccessToken() + " " + responseData.getMessage());

                    // Access token'ı SharedPreferences'a kaydet
                    localStorageManager.saveAccessToken(responseData.getAccessToken());
<<<<<<< Updated upstream


=======
>>>>>>> Stashed changes
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        localStorageManager.saveAccessToken(null);
                        System.err.println("Error Response: " + errorBody);
                        // Hata durumunda özel exception fırlat
                        throw new ApiAuthException("Login işlemi başarısız oldu.");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AccessTokenModel> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir

                System.err.println("Request Failure: " + t.getMessage());
                // Hata durumunda özel exception fırlat
                throw new ApiAuthException("Login işlemi başarısız oldu.");
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

    public void logout() {
        // Retrofit istemcisini oluştur
        Retrofit retrofit = ApiClient.getClient();

        // API servisini oluştur
        ApiServiceInterface apiService = retrofit.create(ApiServiceInterface.class);

        // Access token'ı SharedPreferences veya başka bir kaynaktan al
        String accessToken = localStorageManager.getAccessToken();

        // API'ye POST isteği gönder
        Call<Void> call = apiService.logout("Bearer: " + accessToken);

        // Asenkron olarak isteği gerçekleştir
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // İstek başarılı ise buraya gelir
                if (response.isSuccessful()) {
                    // response verilerini kullan
                    System.out.println("Logout Successfull.");
                    localStorageManager.removeAccessToken();
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
            public void onFailure(Call<Void> call, Throwable t) {
                // İstek başarısız olduğunda buraya gelir
                System.err.println("Request Failure: " + t.getMessage());
            }
        });
    }
}