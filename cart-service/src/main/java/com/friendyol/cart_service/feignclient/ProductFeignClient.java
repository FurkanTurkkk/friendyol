package com.friendyol.cart_service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "product-service")
public interface ProductFeignClient {

    @GetMapping("/api/v1/product/product-price/{productId}")
    public ResponseEntity<BigDecimal> findProductPriceByProductId(@PathVariable("productId")Long productId);

    @GetMapping("/api/v1/product/product-name/{productId}")
    public ResponseEntity<String> findProductNameByProductId(@PathVariable("productId")Long id);
}
