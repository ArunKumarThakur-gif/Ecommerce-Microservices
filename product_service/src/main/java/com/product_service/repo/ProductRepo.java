package com.product_service.repo;


import com.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(long categoryId);

    @Query("SELECT p FROM Product p " +
            "WHERE (:keyword IS NULL OR p.name LIKE CONCAT('%', :keyword, '%') OR p.description LIKE CONCAT('%', :keyword, '%')) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND p.status NOT IN ('DELETED', 'OUT_OF_STOCK')")
    List<Product> searchProducts(@Param("keyword") String keyword,
                                 @Param("minPrice") Double minPrice,
                                 @Param("maxPrice") Double maxPrice);

}
