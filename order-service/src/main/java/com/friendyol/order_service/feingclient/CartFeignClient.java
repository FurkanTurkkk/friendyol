package com.friendyol.order_service.feingclient;

import org.example.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service")
public interface CartFeignClient {
    @GetMapping("/api/v1/cart/user-id/{userId}")
    public ResponseEntity<CartDto> findCartByUserId(@PathVariable("userId")String userId);

    @DeleteMapping("/api/v1/cart/cart-id/{cartId}")
    public ResponseEntity<String> deleteCartByCartId(@PathVariable("cartId")Long cartId);
}
