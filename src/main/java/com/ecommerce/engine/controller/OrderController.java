package com.ecommerce.engine.controller;

import com.ecommerce.engine.dto.CreateOrderRequest;
import com.ecommerce.engine.model.Order;
import com.ecommerce.engine.model.OrderStatus;
import com.ecommerce.engine.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * REST controller for order lifecycle.
 * <p>
 * Demonstrates POST for creation and PUT for status transitions (PENDING → SHIPPED).
 * Orders link {@code user_id} and {@code product_id} foreign keys in PostgreSQL.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /** GET /api/orders — all orders */
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    /** GET /api/orders/{id} */
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    /** GET /api/orders/user/{userId} — purchase history with JOINed user & product */
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderService.findByUserId(userId);
    }

    /**
     * POST /api/orders — place an order.
     * Body: { "userId": 1, "productId": 2, "quantity": 1 }
     */
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody CreateOrderRequest request) {
        Order order = orderService.placeOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    /**
     * PUT /api/orders/{id}/status?status=CONFIRMED
     * Updates order state (fulfillment workflow).
     */
    @PutMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
        return orderService.updateStatus(id, status);
    }
}
