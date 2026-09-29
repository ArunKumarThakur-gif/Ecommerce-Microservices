package com.product_service.services.product_service_impl;

import com.product_service.dto.ProductDto;
import com.product_service.entity.Product;
import com.product_service.enums.ProductStatus;
import com.product_service.exception.ProductNotFoundException;
import com.product_service.mapper.ProductMapper;
import com.product_service.repo.ProductRepo;
import com.product_service.services.product_service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Transactional()
    @Override
    public ProductDto registerProduct(ProductDto productDto) {
        Product product = ProductMapper.toProduct(productDto);
        product.setStatus(ProductStatus.AVAILABLE);
        Product savedProduct = productRepo.save(product);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(long productId) {
        Product product = productRepo.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("product not found " + productId)
        );
        return ProductMapper.toDto(product);
    }

    @Transactional
    @Override
    public ProductDto updateProduct(long productId, ProductDto productDto) {
        Product product = productRepo.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found " + productId)
        );

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategoryId(productDto.getCategoryId());

        // save product
        Product updatedProduct = productRepo.save(product);

        return ProductMapper.toDto(updatedProduct);
    }

    @Transactional
    @Override
    public void deleteProduct(long productId) {
        Product product = productRepo.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("product not found " + productId)
        );

        product.setStatus(ProductStatus.DELETED);
        productRepo.save(product);
    }

    @Override
    public List<ProductDto> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Product> productPage = productRepo.findAll(pageable);

        return productPage.getContent()
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductDto> getProductsByCategory(long categoryId) {
        List<Product> products = productRepo.findByCategoryId(categoryId);
        if(products.isEmpty()) {
            throw new ProductNotFoundException("product not found for category " + categoryId);
        }

        return products.stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductDto> searchProducts(String keyword, Double minPrice, Double maxPrice) {
        List<Product> products = productRepo.searchProducts(keyword, minPrice, maxPrice);

        if(products.isEmpty()) {
            throw new ProductNotFoundException("product not found ");
        }

        return products.stream()
                .map(ProductMapper::toDto)
                .toList();
    }
}
