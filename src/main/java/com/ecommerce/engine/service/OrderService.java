package com.ecommerce.engine.service;

import com.ecommerce.engine.dto.CreateOrderRequest;
import com.ecommerce.engine.model.Order;
import com.ecommerce.engine.model.OrderStatus;
import com.ecommerce.engine.model.Product;
import com.ecommerce.engine.model.User;
import com.ecommerce.engine.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Order placement and status management.
 * <p>
 * Demonstrates a typical flow: validate stock → compute total → persist with JPA relations.
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, UserService userService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Order> findByUserId(Long userId) {
        return orderRepository.findOrdersWithDetailsByUserId(userId);
    }

    /**
     * Creates an order and decrements product stock in one transaction.
     */
    @Transactional
    public Order placeOrder(CreateOrderRequest request) {
        User user = userService.findById(request.getUserId());
        Product product = productService.findById(request.getProductId());
        int quantity = request.getQuantity();

        if ( quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (product.getStockQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
        }

        BigDecimal total = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        product.setStockQuantity(product.getStockQuantity() - quantity);

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(quantity);
        order.setTotalAmount(total);
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    @Transactional
    public Order updateStatus(Long id, OrderStatus status) {
        Order order = findById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
