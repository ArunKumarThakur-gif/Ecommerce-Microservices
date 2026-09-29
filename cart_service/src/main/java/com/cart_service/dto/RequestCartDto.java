package com.cart_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestCartDto {
    private Long productId;
    private Long quantity;
    private double price;
}
