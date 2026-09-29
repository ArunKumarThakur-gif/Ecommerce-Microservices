package com.cart_service.exception;

public class CartItemNotFound extends RuntimeException {
    public CartItemNotFound(String msg) {
        super(msg);
    }
}
