package com.example.onlinebankingappproject.api;

import com.example.onlinebankingappproject.model.request_models.CreateAccountRequestModel;
import com.example.onlinebankingappproject.model.request_models.DepositRequestModel;
import com.example.onlinebankingappproject.model.request_models.PaymentRequestModel;
import com.example.onlinebankingappproject.model.request_models.RegisterRequestModel;
import com.example.onlinebankingappproject.model.request_models.TransferRequestModel;
import com.example.onlinebankingappproject.model.request_models.WithdrawRequestModel;
import com.example.onlinebankingappproject.model.base_models.AccessTokenModel;
import com.example.onlinebankingappproject.model.request_models.LoginRequestModel;
import com.example.onlinebankingappproject.model.base_models.AccountModel;
import com.example.onlinebankingappproject.model.response_models.DashboardResponseModel;
import com.example.onlinebankingappproject.model.response_models.PaymentHistoryResponseModel;
import com.example.onlinebankingappproject.model.response_models.RegisterResponseModel;
import com.example.onlinebankingappproject.model.base_models.TransactionHistoryModel;
import com.example.onlinebankingappproject.model.response_models.TransactionHistoryResponseModel;
import com.example.onlinebankingappproject.model.response_models.TransactionResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiServiceInterface {
    @POST("http://10.0.2.2:8070/register?confirm_password=1234")
    Call<RegisterResponseModel> register(@Body RegisterRequestModel registerRequestModel);

    @POST("http://10.0.2.2:8070/login")
    Call<AccessTokenModel> login(@Body LoginRequestModel loginRequestModel);

    @POST("http://10.0.2.2:8070/transact/deposit")
    Call<TransactionResponseModel> depositTransaction(@Header("Authorization") String accessToken, @Body DepositRequestModel depositRequestModel);

    @POST("http://10.0.2.2:8070/transact/transfer")
    Call<TransactionResponseModel> transferTransaction(@Header("Authorization") String accessToken, @Body TransferRequestModel transferRequestModel);

    @POST("http://10.0.2.2:8070/transact/withdraw")
    Call<TransactionResponseModel> withdrawTransaction(@Header("Authorization") String accessToken, @Body WithdrawRequestModel withdrawRequestModel);

    @POST("http://10.0.2.2:8070/transact/payment")
    Call<TransactionResponseModel> paymentTransaction(@Header("Authorization") String accessToken, @Body PaymentRequestModel paymentRequestModel);

    @POST("http://10.0.2.2:8070/account/create_account")
    Call<List<AccountModel>> createAccount(@Header("Authorization") String accessToken, @Body CreateAccountRequestModel createAccountRequestModel);

    @GET("http://10.0.2.2:8070/app/dashboard")
    Call<DashboardResponseModel> getDashboard(@Header("Authorization") String accessToken);

    @GET("http://10.0.2.2:8070/app/payment_history")
    Call<PaymentHistoryResponseModel> getPaymentHistory(@Header("Authorization") String accessToken);

    @GET("http://10.0.2.2:8070/app/transaction_history")
    Call<TransactionHistoryResponseModel> getTransactionHistory(@Header("Authorization") String accessToken);

    @GET("http://10.0.2.2:8070/app/transaction_history")
    Call<List<TransactionHistoryModel>> getAccountTransactionHistory();

    @GET("http://10.0.2.2:8070/logout")
    Call<Void> logout(@Header("Authorization") String accessToken);
}