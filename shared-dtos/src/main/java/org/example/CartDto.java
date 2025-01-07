package org.example;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {

    private Long cartId;
    private BigDecimal price;
    private List<CartItemDto> cartItemDtoList;

    public CartDto() {
    }

    public CartDto(Long cartId,BigDecimal price, List<CartItemDto> cartItemDtoList) {
        this.cartId=cartId;
        this.price = price;
        this.cartItemDtoList = cartItemDtoList;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
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
