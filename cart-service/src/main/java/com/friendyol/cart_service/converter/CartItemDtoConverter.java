package com.friendyol.cart_service.converter;

import com.friendyol.cart_service.model.CartItem;
import com.friendyol.cart_service.util.FeignClientService;
import org.example.CartItemDto;
import org.springframework.stereotype.Component;

@Component
public class CartItemDtoConverter {
    private final FeignClientService feignClientService;

    public CartItemDtoConverter(FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    public CartItemDto convert(CartItem cartItem){
        String productName= feignClientService.findProductNameByProductId(cartItem.getProductId());
        return new CartItemDto(
             productName,
             cartItem.getQuantity(),
             cartItem.getPrice()
        );
    }
}
