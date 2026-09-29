package com.cart_service.mapper;

import com.cart_service.dto.ResponseCartDto;
import com.cart_service.dto.ResponseCartItemDto;
import com.cart_service.entity.Cart;
import com.cart_service.entity.CartItem;

import java.math.BigDecimal;


public class CartMapper {

    public static ResponseCartDto toResponseCartDto(Cart cart) {
        return ResponseCartDto.builder()
                .cartId(cart.getId())
                .userId(cart.getUserId())
                .subTotal(cart.getPrice())
                .items(
                        cart.getCartItems()
                                .stream()
                                .map(CartMapper::toCartItemDto)
                                .toList()
                )
                .updatedAt(cart.getUpdatedAt())
                .build();
    }

    public static ResponseCartItemDto toCartItemDto(CartItem cartItem) {
        return ResponseCartItemDto.builder()
                .id(cartItem.getId())
                .productId(cartItem.getProductId())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .subTotal(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .build();
    }
}
