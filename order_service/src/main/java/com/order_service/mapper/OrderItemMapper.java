package com.order_service.mapper;

import com.order_service.dto.OrderItemDto;
import com.order_service.dto.ResponseCartItemDto;
import com.order_service.entity.OrderItem;

public class OrderItemMapper {
    public static OrderItem toEntity(ResponseCartItemDto item) {
        return OrderItem.builder()
                .productId(item.getProductId())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .build();
    }

    public static OrderItemDto toDto(OrderItem item) {

    }
}
