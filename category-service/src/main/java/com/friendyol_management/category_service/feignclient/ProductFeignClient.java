package com.friendyol_management.category_service.feignclient;


import org.example.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductFeignClient {

    @GetMapping("/api/v1/product/products/{categoryId}")
    public ResponseEntity<List<ProductDto>> findProductListByCategoryId(@PathVariable("categoryId")String categoryId);

}
