package com.friendyol.cart_service.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private Long productId;
    private int quantity;
    private BigDecimal price;


    public CartItem() {
    }

    public CartItem(Cart cart, Long productId, int quantity) {
        this.cart = cart;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void updatePrice(BigDecimal productPrice){
        this.price=productPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public void updateQuantity(int quantity){
        this.quantity+=quantity;
    }
}
