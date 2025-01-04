package com.friendyol.stock_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long productId;
    private Long quantity;
    private LocalDate updatedDate;

    public Stock() {
    }

    public Stock(Long productId, Long quantity, LocalDate updatedDate) {
        this.productId = productId;
        this.quantity = quantity;
        this.updatedDate = updatedDate;
    }

    public String getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void increaseQuantity(Long amount){
        this.quantity+=amount;
    }

    public void decreaseQuantity(Long amount){
        if(this.quantity<amount){
            throw new RuntimeException("Yeterli stok bulunmuyor");
        }
        this.quantity-=amount;
    }
}
