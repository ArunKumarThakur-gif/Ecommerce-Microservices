package com.cart_service.globalException;


import com.cart_service.enums.ErrorCode;
import com.cart_service.exception.ProductAlreadyInCart;
import com.cart_service.exception.QuantityExceedException;
import com.cart_service.util.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ProductAlreadyInCart.class)
    public ResponseEntity<ErrorResponse> productAlreadyInCart(HttpServletRequest req) {
        ErrorResponse error = ErrorResponse.builder()
                .errorCode(ErrorCode.PRODUCT_ALREADY_IN_THE_CART)
                .message("Product already in the cart")
                .status(HttpStatus.CONFLICT.value())
                .path(req.getRequestURI())
                .correlationId(UUID.randomUUID().toString())
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(QuantityExceedException.class)
    public ResponseEntity<ErrorResponse> quantityExceed(HttpServletRequest req) {
        ErrorResponse error = ErrorResponse.builder()
                .errorCode(ErrorCode.QUANTITY_EXCEED)
                .message("You can only purchase 5 units")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(req.getRequestURI())
                .correlationId(UUID.randomUUID().toString())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
