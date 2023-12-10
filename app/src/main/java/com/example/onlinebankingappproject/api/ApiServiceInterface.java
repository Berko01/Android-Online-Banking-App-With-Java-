package com.example.onlinebankingappproject.api;

import com.example.onlinebankingappproject.model.RequestModels.CreateAccountRequestModel;
import com.example.onlinebankingappproject.model.RequestModels.DepositRequestModel;
import com.example.onlinebankingappproject.model.RequestModels.PaymentRequestModel;
import com.example.onlinebankingappproject.model.RequestModels.RegisterRequestModel;
import com.example.onlinebankingappproject.model.RequestModels.TransferRequestModel;
import com.example.onlinebankingappproject.model.RequestModels.WithdrawRequestModel;
import com.example.onlinebankingappproject.model.ResponseModels.AccessTokenModel;
import com.example.onlinebankingappproject.model.RequestModels.LoginRequestModel;
import com.example.onlinebankingappproject.model.ResponseModels.AccountModel;
import com.example.onlinebankingappproject.model.ResponseModels.DashboardResponseModel;
import com.example.onlinebankingappproject.model.ResponseModels.RegisterResponseModel;
import com.example.onlinebankingappproject.model.ResponseModels.TransactionResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServiceInterface {

    @POST("http://10.0.2.2:8070/register?confirm_password=1234")
    Call<RegisterResponseModel> register(@Body RegisterRequestModel registerRequestModel);

    @POST("http://10.0.2.2:8070/login")
    Call<AccessTokenModel> login(@Body LoginRequestModel loginRequestModel);

    @POST("http://10.0.2.2:8070/transact/deposit")
    Call<TransactionResponseModel> depositTransaction(@Body DepositRequestModel depositRequestModel);

    @POST("http://10.0.2.2:8070/transact/transfer?")
    Call<TransactionResponseModel> transferTransaction(@Body TransferRequestModel transferRequestModel);

    @POST("http://10.0.2.2:8070/transact/withdraw")
    Call<TransactionResponseModel> withdrawTransaction(@Body WithdrawRequestModel withdrawRequestModel);

    @POST("http://10.0.2.2:8070/transact/payment")
    Call<TransactionResponseModel> paymentTransaction(@Body PaymentRequestModel paymentRequestModel);

    @POST("http://10.0.2.2:8070/account/create_account")
    Call<AccountModel> createAccount(@Body CreateAccountRequestModel createAccountRequestModel);


    @GET("http://10.0.2.2:8070/app/dashboard")
    Call<DashboardResponseModel> getDashboard();

    @GET("http://10.0.2.2:8070/app/payment_history")
    Call<TransactionResponseModel> getPaymentHistory();

    @GET("http://10.0.2.2:8070/app/transaction_history")
    Call<TransactionResponseModel> getTransactionHistory();

    @GET("http://10.0.2.2:8070/app/transaction_history")
    Call<TransactionResponseModel> getAccountHistory();

    @GET("http://10.0.2.2:8070/app/logout")
    Call logout();

}
