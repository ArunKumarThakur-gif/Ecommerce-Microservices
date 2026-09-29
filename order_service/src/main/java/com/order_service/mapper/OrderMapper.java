package com.order_service.mapper;


import com.order_service.dto.OrderDto;
import com.order_service.entity.Order;

public class OrderMapper {
    public static OrderDto toDto(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .cartId(order.getCartId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .paymentMode(order.getPaymentMode())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .shippingAddress(ShippingAddressMapper.toDto(order.getShippingAddress()))
                .items(
                        order.getItems()
                                .stream()
                                .map(OrderItemMapper::toDto)
                                .toList())
                .build();

    }
}
