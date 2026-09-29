package com.order_service.dto;

import com.order_service.enums.OrderStatus;
import com.order_service.enums.PaymentMode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
public class OrderDto {

    private Long orderId;
    private Long userId;
    private Long cartId;
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ShippingAddressDto shippingAddress;
    private List<OrderItemDto> items;
}
