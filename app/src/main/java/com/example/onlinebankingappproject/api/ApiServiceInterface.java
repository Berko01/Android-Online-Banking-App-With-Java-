package com.example.onlinebankingappproject.api;

import com.example.onlinebankingappproject.model.AccessTokenModel;
import com.example.onlinebankingappproject.model.LoginRequestModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServiceInterface {

    @POST("http://10.0.2.2:8070/login")
    Call<AccessTokenModel> login(@Body LoginRequestModel loginRequestModel);
}
