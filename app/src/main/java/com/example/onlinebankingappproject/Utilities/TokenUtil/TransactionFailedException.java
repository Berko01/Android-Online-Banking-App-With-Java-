package com.example.onlinebankingappproject.Utilities.TokenUtil;

public class TransactionFailedException  extends  RuntimeException{
    public TransactionFailedException(String message) {
        super(message);
    }
}