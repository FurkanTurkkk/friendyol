package com.friendyol.product_service.converter;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.friendyol.product_service.feignclient.CategoryFeignClient;
import com.friendyol.product_service.model.Product;
import org.example.ProductDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DtoConverter {

    private final CategoryFeignClient categoryFeignClient;

    public DtoConverter(CategoryFeignClient categoryFeignClient) {
        this.categoryFeignClient = categoryFeignClient;
    }

    @JsonCreator
    public ProductDto convert(Product product){
        String categoryName=categoryFeignClient.findCategoryNameByCategoryId(product.getCategoryId()).getBody();
        return new ProductDto(
                product.getName(),
                15L,
                product.getPrice(),
                categoryName,
                product.getColor(),
                product.getDescription()
        );
    }
}
