package com.order_service.entity;

import com.order_service.enums.OrderStatus;
import com.order_service.enums.PaymentMode;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long userId;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private Long cartId; // optional

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Embedded
    private ShippingAddress shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
