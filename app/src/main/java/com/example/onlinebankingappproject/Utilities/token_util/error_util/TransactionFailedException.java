package com.example.onlinebankingappproject.Utilities.token_util.error_util;

public class TransactionFailedException  extends  RuntimeException{
    public TransactionFailedException(String message) {
        super(message);
    }
}