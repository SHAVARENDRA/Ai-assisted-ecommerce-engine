package com.ecommerce.engine.repository;

import com.ecommerce.engine.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository for catalog {@link Product} entities.
 * <p>
 * Hibernate translates method names like {@code findByCategory} into SQL WHERE clauses.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryIgnoreCase(String category);

    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
