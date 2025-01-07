package org.example;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {

    private String cartId;
    private BigDecimal price;
    private List<CartItemDto> cartItemDtoList;

    public CartDto() {
    }

    public CartDto(BigDecimal price, List<CartItemDto> cartItemDtoList) {
        this.price = price;
        this.cartItemDtoList = cartItemDtoList;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<CartItemDto> getCartItemDtoList() {
        return cartItemDtoList;
    }

    public void setCartItemDtoList(List<CartItemDto> cartItemDtoList) {
        this.cartItemDtoList = cartItemDtoList;
    }
}
