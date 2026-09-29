package com.order_service.clients;

import com.order_service.dto.ResponseCartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("CART-SERVICE")
public interface CartClient {

    @GetMapping("/get-cart-by-cartid")
    public ResponseEntity<ResponseCartDto> getCartById(@RequestParam Long cartId);
}
