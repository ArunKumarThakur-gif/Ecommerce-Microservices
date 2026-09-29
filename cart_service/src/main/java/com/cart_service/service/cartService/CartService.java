package com.cart_service.service.cartService;

import com.cart_service.dto.RequestCartDto;
import com.cart_service.dto.ResponseCartDto;
import com.cart_service.dto.ResponseCartItemDto;
import com.cart_service.entity.Cart;

public interface CartService {
    ResponseCartItemDto addToCart(Long userId, RequestCartDto requestCartDto);
    ResponseCartDto removeFromCart(Long userId, Long cartItemId);
    ResponseCartItemDto updateQuantityInCartItem(Long userId, Long cartItemId, Integer quantity);
    ResponseCartDto viewCart(Long userId);

    ResponseCartDto getCartByCartId(Long cartId);
}
