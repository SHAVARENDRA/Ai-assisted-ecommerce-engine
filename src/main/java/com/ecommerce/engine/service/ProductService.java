package com.ecommerce.engine.service;

import com.ecommerce.engine.model.Product;
import com.ecommerce.engine.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Catalog operations: list, search by keyword/category, and maintain inventory.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Product> searchByKeyword(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Transactional(readOnly = true)
    public List<Product> findByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    @Transactional
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(Long id, Product updated) {
        Product existing = findById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setCategory(updated.getCategory());
        existing.setPrice(updated.getPrice());
        existing.setStockQuantity(updated.getStockQuantity());
        return productRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.delete(findById(id));
    }
}
