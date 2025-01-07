package org.example;

import java.math.BigDecimal;

public class CartItemDto {

    private String productName;
    private int quantity;
    private BigDecimal price;

    public CartItemDto() {
    }

    public CartItemDto(String productName, int quantity, BigDecimal price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
