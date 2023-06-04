package com.example.RaiseTech11.exception;

public class ResponseError {
    private int status;
    private String message;

    public ResponseError(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
