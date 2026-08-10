package com.ecommerce.engine.model;

/**
 * Lifecycle states for an {@link Order}.
 */
public enum OrderStatus {
    PENDING,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
