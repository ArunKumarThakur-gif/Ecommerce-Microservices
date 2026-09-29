package com.cart_service.exception;

public class QuantityExceedException extends RuntimeException {
    public QuantityExceedException(String msg) {
        super(msg);
    }
}
