package com.product_service.services.product_service;

import com.product_service.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto registerProduct(ProductDto productDto);
    ProductDto getProductById(long productId);
    ProductDto updateProduct(long productId, ProductDto productDto);
    void deleteProduct(long productId);
    List<ProductDto> getAllProducts(int page, int size);
    List<ProductDto> getProductsByCategory(long categoryId);
    List<ProductDto> searchProducts(String keyword, Double minPrice, Double maxPrice);
}
