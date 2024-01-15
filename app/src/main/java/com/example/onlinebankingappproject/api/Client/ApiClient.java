package com.example.onlinebankingappproject.api.Client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://10.0.2.2:8070"; // API'nin temel URL'si

    private static Retrofit retrofit = null;

    // Retrofit istemcisini oluşturan ve döndüren metot
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) // JSON verisini Java nesnelerine dönüştürmek için Gson kullanılır
                    .build();
        }
        return retrofit;
    }
}
