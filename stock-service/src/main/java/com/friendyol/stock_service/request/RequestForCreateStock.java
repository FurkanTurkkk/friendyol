package com.friendyol.stock_service.request;

public class RequestForCreateStock {
    private Long productId;
    private Long quantity;

    public RequestForCreateStock() {
    }

    public RequestForCreateStock(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getQuantity() {
        return quantity;
    }
}
