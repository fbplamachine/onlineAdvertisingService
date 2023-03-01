package com.fbplamachine.onlineAdvertisingService.exceptions;

import org.springframework.http.HttpStatus;

public class StateException extends ApiRequestException{
    public StateException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }
}
