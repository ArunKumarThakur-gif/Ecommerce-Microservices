package com.order_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class ResponseCartItemDto {
    private long id;
    private long productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
    private LocalDateTime updatedAt;
}
