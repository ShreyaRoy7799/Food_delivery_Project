package com.fooddeliveryapp.foodappbackend.exception;

public class RestaurantNotFoundException extends Exception {
    public RestaurantNotFoundException() {
        super();
    }

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestaurantNotFoundException(Throwable cause) {
        super(cause);
    }

    protected RestaurantNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
