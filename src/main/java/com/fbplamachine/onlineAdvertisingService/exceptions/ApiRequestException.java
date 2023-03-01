package com.fbplamachine.onlineAdvertisingService.exceptions;

import org.springframework.http.HttpStatus;

public abstract class ApiRequestException extends RuntimeException{
    private HttpStatus statusCode;
    public ApiRequestException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
