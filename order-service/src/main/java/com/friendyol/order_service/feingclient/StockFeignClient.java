package com.friendyol.order_service.feingclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("stock-service")
public interface StockFeignClient {

    @PutMapping("/api/v1/stock/update-stock/{productId}")
    public void updateStock(@PathVariable("productId")Long productId,@RequestBody Long quantity);
}
