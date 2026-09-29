package com.cart_service.exception;

public class ProductAlreadyInCart extends RuntimeException {
    public ProductAlreadyInCart(String msg) {
        super(msg);
    }
}
