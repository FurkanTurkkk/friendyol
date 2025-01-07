package com.friendyol.cart_service.request;

public class RequestForCreateCartItem {

    private Long cartId;
    private Long productId;
    private int quantity;

    public RequestForCreateCartItem() {
    }

    public RequestForCreateCartItem(Long cartId, Long productId, int quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
