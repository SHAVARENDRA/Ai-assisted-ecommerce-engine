package com.ecommerce.engine.dto;

/**
 * Payload for placing a new order via POST /api/orders.
 */
public class CreateOrderRequest {

    private Long userId;
    private Long productId;
    private Integer quantity;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(Long userId, Long productId, Integer quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
