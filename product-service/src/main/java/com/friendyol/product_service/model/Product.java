package com.friendyol.product_service.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String categoryId;
    private Long supplierId;
    private String color;
    private String description;
    private BigDecimal price;

    public Product() {
        this.color="-";
    }

    public Product(String name, String categoryId, Long supplierId, String color, String description, BigDecimal price) {
        this.name = name;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.color = color;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(color, product.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }


}
