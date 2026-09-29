package com.order_service.service.orderService;

import com.order_service.dto.OrderDto;
import com.order_service.dto.ResponseAddressDto;
import com.order_service.dto.ShippingAddressDto;


public interface OrderService {
    OrderDto orderFromCart(Long cartId);
    ResponseAddressDto addShippingAddress(ShippingAddressDto dto);
}
