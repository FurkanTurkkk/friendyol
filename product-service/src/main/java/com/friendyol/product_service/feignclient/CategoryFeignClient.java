package com.friendyol.product_service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "category-service")
public interface CategoryFeignClient {

    @GetMapping("/api/v1/category/categoryName/{categoryId}")
    public ResponseEntity<String> findCategoryNameByCategoryId(@PathVariable("categoryId") String categoryId);

}
