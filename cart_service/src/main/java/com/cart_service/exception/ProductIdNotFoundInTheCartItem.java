package com.cart_service.exception;

public class ProductIdNotFoundInTheCartItem extends RuntimeException {
    public ProductIdNotFoundInTheCartItem(String msg) {
        super(msg);
    }
}
