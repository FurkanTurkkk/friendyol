package com.friendyol.cart_service.converter;

import com.friendyol.cart_service.model.Cart;
import org.example.CartDto;
import org.springframework.stereotype.Component;

@Component
public class CartDtoConverter {
    private final CartItemDtoConverter converter;

    public CartDtoConverter(CartItemDtoConverter converter) {
        this.converter = converter;
    }

    public CartDto convert(Cart cart){
        return new CartDto(
                cart.getId(),
                cart.getPrice(),
                cart.getCartItemList().stream()
                        .map(converter::convert).toList()
        );
    }
}
