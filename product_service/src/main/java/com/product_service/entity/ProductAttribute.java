package com.product_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class ProductAttribute {

    @Id
    @SequenceGenerator(name = "product_att_seq", sequenceName = "product_att_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_att_seq")
    private long id;

    private String key;
    private String value;

    @PositiveOrZero
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
