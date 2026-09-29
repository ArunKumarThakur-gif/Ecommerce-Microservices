package com.cart_service.controller;


import com.cart_service.dto.RequestCartDto;
import com.cart_service.dto.ResponseCartDto;
import com.cart_service.dto.ResponseCartItemDto;
import com.cart_service.exception.QuantityExceedException;
import com.cart_service.service.cartService.CartServiceImpl;
import com.cart_service.service.jwtService.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartServiceImpl cartService;
    private final JwtService jwtService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<ResponseCartItemDto> addToCart(@RequestBody RequestCartDto requestCartDto, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        Long userId = jwtService.extractUserId(token);
        ResponseCartItemDto responseCartItemDto = cartService.addToCart(userId, requestCartDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseCartItemDto);
    }

    @PatchMapping("/update-quantity")
    public ResponseEntity<?> updateQuantity(@RequestParam long cartItemId, @RequestParam int quantity, @RequestHeader("Authorization") String authHeader) {
        if(quantity > 5) {
            throw new QuantityExceedException("You can only purchase 5 units");
        }

        String token = authHeader.substring(7);
        Long userId = jwtService.extractUserId(token);
        ResponseCartItemDto responseCartItemDto = cartService.updateQuantityInCartItem(userId, cartItemId, quantity);

        return ResponseEntity.status(HttpStatus.OK).body(responseCartItemDto);
    }

    @GetMapping("/view-cart")
    public ResponseEntity<?> getAllCart(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        long userId = jwtService.extractUserId(token);

        ResponseCartDto responseCartDto = cartService.viewCart(userId);

        return ResponseEntity.status(HttpStatus.OK).body(responseCartDto);
    }

    @GetMapping("/get-cart-by-cartid")
    public ResponseEntity<ResponseCartDto> getCartById(@RequestParam Long cartId) {
        ResponseCartDto cart = cartService.getCartByCartId(cartId);

        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }

}
