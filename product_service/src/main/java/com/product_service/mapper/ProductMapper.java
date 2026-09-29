package com.product_service.mapper;

import com.product_service.dto.ProductDto;
import com.product_service.entity.Product;
import com.product_service.enums.ProductStatus;

public class ProductMapper {
    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setProductId(product.getProductId());
        dto.setSku(product.getSku());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStatus(String.valueOf(product.getStatus()));
        dto.setCategoryId(product.getCategoryId());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }

    public static Product toProduct(ProductDto productDto) {
        Product product = new Product();
        product.setSku(productDto.getSku());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategoryId(productDto.getCategoryId());
        return product;
    }


}
