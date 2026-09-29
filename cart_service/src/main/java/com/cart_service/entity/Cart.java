package com.cart_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @SequenceGenerator(name = "cart_seq", sequenceName = "CART_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    private Long id;

    @Column(nullable = false)
    @NotNull
    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private BigDecimal price;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<CartItem> cartItems = new ArrayList<>();


    @PrePersist
    public void createdAt() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void updatedAt() {
        updatedAt = LocalDateTime.now();
    }
}
