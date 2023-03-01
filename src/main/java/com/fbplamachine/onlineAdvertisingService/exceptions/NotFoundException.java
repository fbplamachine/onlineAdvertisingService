package com.fbplamachine.onlineAdvertisingService.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiRequestException {
    public NotFoundException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }
}
