package com.cart_service.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
public class ResponseCartDto {
    private Long cartId;
    private Long userId;
    private BigDecimal subTotal;
    private List<ResponseCartItemDto> items;
    private LocalDateTime updatedAt;
}
