package org.example;

import java.math.BigDecimal;

public class ProductDto {
    private String name;
    private Long stock;
    private BigDecimal price;
    private String categoryName;
    private String color;
    private String description;

    public ProductDto() {
    }

    public ProductDto(String name, Long stock, BigDecimal price, String categoryName, String color, String description) {
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.categoryName = categoryName;
        this.color = color;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
