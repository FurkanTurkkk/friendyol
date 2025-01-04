package org.example;

public class ProductStockDto {

    private Long quantity;

    public ProductStockDto() {
    }

    public ProductStockDto(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
