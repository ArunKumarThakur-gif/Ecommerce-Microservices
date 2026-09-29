package com.cart_service.clients;

import com.cart_service.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PRODUCT-SERVICE")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable Long id);
}
