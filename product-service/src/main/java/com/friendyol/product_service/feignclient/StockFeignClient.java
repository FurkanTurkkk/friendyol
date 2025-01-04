package com.friendyol.product_service.feignclient;

import org.example.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "stock-service")
public interface StockFeignClient {
    @GetMapping("/api/v1/stock/product/{productId}")
    public ResponseEntity<StockDto> findStockInformationByProductId(@PathVariable("productId")Long productId);

    @PostMapping("/api/v1/stock/product/{productId}/quantity")
    public ResponseEntity<StockDto> createStock(@PathVariable("productId")Long productId,@RequestBody Long quantity);

    @DeleteMapping("/api/v1/stock/delete-product-by-productId/{productId}")
    public ResponseEntity<String> deleteStockInformationByProductId(@PathVariable("productId")Long productId);

}
