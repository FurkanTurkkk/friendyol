package org.example;

import java.math.BigDecimal;

public class ProductDto {
    private String name;
    private Long stock;
    private BigDecimal price;
    private String categoryName;
    private String color;
    private String description;


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

    public Long getStock() {
        return stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }
}
