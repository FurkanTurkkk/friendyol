package com.friendyol.stock_service.feignclient;

import org.example.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductFeignClient {
    @GetMapping("/api/v1/product/{productId}")
    public ResponseEntity<ProductDto> findProductByProductId(@PathVariable("productId")Long id);

    @GetMapping("/api/v1/product/product-name/{productId}")
    public ResponseEntity<String> findProductNameByProductId(@PathVariable("productId")Long id);
}
