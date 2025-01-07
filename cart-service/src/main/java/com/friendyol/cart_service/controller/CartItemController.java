package com.friendyol.cart_service.controller;

import com.friendyol.cart_service.request.RequestForCreateCartItem;
import com.friendyol.cart_service.service.CartItemService;
import org.example.CartItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart-item")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public ResponseEntity<CartItemDto> createCartItem(@RequestBody RequestForCreateCartItem request){
        return ResponseEntity.ok(cartItemService.createCartItem(request));
    }
}
