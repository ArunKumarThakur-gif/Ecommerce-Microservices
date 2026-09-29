package com.product_service.entity;

import com.product_service.enums.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @SequenceGenerator(name = "prod_seq", sequenceName = "prod_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_seq")
    private long productId;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String sku;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Size(max = 1000)
    private String description;

    @Column(nullable = false)
    @Positive
    private BigDecimal price;

    @PositiveOrZero
    private Integer stockQuantity;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    private long categoryId;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAttribute> productAttributeList;

}
