package com.friendyol.cart_service.util;

import com.friendyol.cart_service.feignclient.ProductFeignClient;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FeignClientService {
    private final ProductFeignClient productFeignClient;

    public FeignClientService(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    public String findProductNameByProductId(Long productId){
        return productFeignClient.findProductNameByProductId(productId).getBody();
    }

    public BigDecimal findProductPriceByProductId(Long productId){
        return productFeignClient.findProductPriceByProductId(productId).getBody();
    }
}
