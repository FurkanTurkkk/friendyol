package com.friendyol.product_service.request;


import java.math.BigDecimal;

public class RequestForCreateProduct {

    private String name;
    private String categoryId;
    private Long supplierId;
    private String color;
    private String description;
    private BigDecimal price;

    public RequestForCreateProduct(String name,
                                   String categoryId,
                                   Long supplierId,
                                   String color,
                                   String description,
                                   BigDecimal price) {
        this.name = name;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.color = color;
        this.description = description;
        this.price = price;
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
}
