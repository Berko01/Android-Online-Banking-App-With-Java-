package com.example.onlinebankingappproject.model.base_models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserModel {
    private String user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String token;
    private String code;
    private int verified;
    private LocalDate verified_at;
    private LocalDateTime create_at;
    private LocalDateTime updated_at;

    public UserModel(String user_id, String first_name, String last_name, String email, String password, String token, String code, int verified, LocalDate verified_at, LocalDateTime create_at, LocalDateTime updated_at) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.token = token;
        this.code = code;
        this.verified = verified;
        this.verified_at = verified_at;
        this.create_at = create_at;
        this.updated_at = updated_at;
    }
}
