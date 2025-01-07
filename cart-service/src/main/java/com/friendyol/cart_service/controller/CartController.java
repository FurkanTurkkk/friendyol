package com.friendyol.cart_service.controller;

import com.friendyol.cart_service.request.RequestForCreateCart;
import com.friendyol.cart_service.service.CartService;
import org.example.CartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody RequestForCreateCart request){
        return ResponseEntity.ok(cartService.createCart(request));
    }

    @GetMapping("/cart-id/{cartId}")
    public ResponseEntity<CartDto> findCartByCartId(@PathVariable("cartId")Long cartId){
        return ResponseEntity.ok(cartService.getCartByCartId(cartId));
    }
}
