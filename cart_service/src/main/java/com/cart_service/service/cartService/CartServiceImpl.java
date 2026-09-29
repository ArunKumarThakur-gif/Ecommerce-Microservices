package com.cart_service.service.cartService;

import com.cart_service.clients.ProductClient;
import com.cart_service.dto.ProductDto;
import com.cart_service.dto.RequestCartDto;
import com.cart_service.dto.ResponseCartDto;
import com.cart_service.dto.ResponseCartItemDto;
import com.cart_service.entity.Cart;
import com.cart_service.entity.CartItem;
import com.cart_service.exception.CartItemNotFound;
import com.cart_service.exception.CartNotFoundException;
import com.cart_service.exception.ProductAlreadyInCart;
import com.cart_service.exception.ProductIdNotFoundInTheCartItem;
import com.cart_service.mapper.CartMapper;
import com.cart_service.repo.CartRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final ProductClient productClient;

    @Override
    @Transactional
    public ResponseCartItemDto addToCart(Long userId, RequestCartDto requestCartDto) {
        ProductDto productDto = productClient.getProductById(requestCartDto.getProductId()).getBody();

        Cart cart = cartRepo.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return cartRepo.save(newCart);
        });

        CartItem existingCartItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProductId().equals(requestCartDto.getProductId()))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            throw new ProductAlreadyInCart("Product already in the cart");
        }

        CartItem cartItem = new CartItem();
        cartItem.setProductId(requestCartDto.getProductId());
        cartItem.setQuantity(1);

        if (productDto == null) {
            throw new ProductIdNotFoundInTheCartItem("Product not found");
        }
        cartItem.setPrice(productDto.getPrice()); // unit price snapshot
        cartItem.setCart(cart);

        cart.getCartItems().add(cartItem);

        recalculateCartTotal(cart);

        Cart savedCart = cartRepo.save(cart);

        return CartMapper.toCartItemDto(cartItem);
    }

    @Override
    @Transactional
    public ResponseCartDto removeFromCart(Long userId, Long cartItemId) {
        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFound("Cart item not found"));

        cart.getCartItems().remove(cartItem);

        recalculateCartTotal(cart);

        Cart savedCart = cartRepo.save(cart);

        return CartMapper.toResponseCartDto(savedCart);
    }

    @Override
    @Transactional
    public ResponseCartItemDto updateQuantityInCartItem(Long userId, Long cartItemId, Integer quantity) {
        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("No cart is associated with this user"));

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFound("Cart item not found"));

        ProductDto productDto = productClient.getProductById(cartItem.getProductId()).getBody();


        if (productDto == null) {
            throw new ProductIdNotFoundInTheCartItem("Product not found");
        }

        cartItem.setQuantity(quantity);
        cartItem.setPrice(productDto.getPrice()); // keep unit price snapshot

        recalculateCartTotal(cart);

        Cart savedCart = cartRepo.save(cart);

        return CartMapper.toCartItemDto(cartItem);
    }

    @Override
    public ResponseCartDto viewCart(Long userId) {
        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));

        return CartMapper.toResponseCartDto(cart);
    }

    @Override
    public ResponseCartDto getCartByCartId(Long cartId) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));

        return CartMapper.toResponseCartDto(cart);
    }


    private void recalculateCartTotal(Cart cart) {
        BigDecimal totalPrice = cart.getCartItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cart.setPrice(totalPrice);
    }
}
