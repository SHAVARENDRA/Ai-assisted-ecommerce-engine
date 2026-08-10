package com.ecommerce.engine.controller;

import com.ecommerce.engine.model.Product;
import com.ecommerce.engine.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for the product catalog.
 * <p>
 * Supports classic keyword search ({@code ?keyword=}) and category filters.
 * AI-powered search lives under {@link AiController}.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /** GET /api/products — full catalog from PostgreSQL via JPA */
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    /** GET /api/products/{id} — single product details */
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    /** GET /api/products/search?keyword=laptop — SQL LIKE query via Spring Data JPA */
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productService.searchByKeyword(keyword);
    }

    /** GET /api/products/category?category=Electronics */
    @GetMapping("/category")
    public List<Product> getByCategory(@RequestParam String category) {
        return productService.findByCategory(category);
    }

    /** POST /api/products — add a new catalog item */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /** PUT /api/products/{id} — update price, stock, description, etc. */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    /** DELETE /api/products/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
