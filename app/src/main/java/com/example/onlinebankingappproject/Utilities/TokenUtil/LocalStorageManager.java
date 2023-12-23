package com.example.onlinebankingappproject.Utilities.TokenUtil;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorageManager {

    private static final String PREF_NAME = "MyPrefs";
    private static final String ACCESS_TOKEN_KEY = "accessToken";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public LocalStorageManager(Context context) {
        // SharedPreferences nesnesini oluştur
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // SharedPreferences'a yazma işlemi için bir editor oluştur
        editor = sharedPreferences.edit();
    }

    public void saveAccessToken(String accessToken) {
        // Access token'ı "accessToken" anahtarıyla kaydet
        editor.putString(ACCESS_TOKEN_KEY, accessToken);

        // Değişiklikleri kaydet
        editor.apply();
    }

    public String getAccessToken() {
        // Access token'ı SharedPreferences'dan al
        return sharedPreferences.getString(ACCESS_TOKEN_KEY,null);
}
}