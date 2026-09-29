package com.cart_service.dto;

import com.cart_service.entity.CartItem;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseCartItemDto {
    private long id;
    private long productId;
    private long quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
    private LocalDateTime updatedAt;
}
