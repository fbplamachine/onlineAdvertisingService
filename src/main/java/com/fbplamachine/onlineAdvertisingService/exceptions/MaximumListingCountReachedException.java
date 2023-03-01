package com.fbplamachine.onlineAdvertisingService.exceptions;

import org.springframework.http.HttpStatus;

public class MaximumListingCountReachedException extends ApiRequestException{
    public MaximumListingCountReachedException(String message, HttpStatus statusCode) {
        super(message, statusCode);
    }
}
