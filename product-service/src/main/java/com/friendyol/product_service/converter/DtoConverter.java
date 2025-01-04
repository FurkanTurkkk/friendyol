package com.friendyol.product_service.converter;


import com.friendyol.product_service.feignclient.CategoryFeignClient;
import com.friendyol.product_service.model.Product;
import com.friendyol.product_service.util.FeignClientService;
import org.example.ProductDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DtoConverter {

    private final FeignClientService feignClientService;

    public DtoConverter(FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    public ProductDto convert(Product product){
        String categoryName= feignClientService.getCategoryNameByCategoryId(product);
        Long quantity= feignClientService.getStockQuantity(product);
        return new ProductDto(
                product.getName(),
                quantity,
                product.getPrice(),
                categoryName,
                product.getColor(),
                product.getDescription()
        );
    }
}
