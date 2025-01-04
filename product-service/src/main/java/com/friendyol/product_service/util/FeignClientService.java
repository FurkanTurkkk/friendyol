package com.friendyol.product_service.util;

import com.friendyol.product_service.feignclient.CategoryFeignClient;
import com.friendyol.product_service.feignclient.StockFeignClient;
import com.friendyol.product_service.model.Product;
import org.springframework.stereotype.Component;

@Component
public class FeignClientService {

    private final StockFeignClient stockFeignClient;
    private final CategoryFeignClient categoryFeignClient;

    public FeignClientService(StockFeignClient stockFeignClient, CategoryFeignClient categoryFeignClient) {
        this.stockFeignClient = stockFeignClient;
        this.categoryFeignClient = categoryFeignClient;
    }

    public Long getStockQuantity(Product product){
        return stockFeignClient.findStockInformationByProductId(product.getId()).getBody().getQuantity();
    }

    public void createStock(Long productId,Long quantity){
        stockFeignClient.createStock(productId,quantity);
    }

    public void deleteStockInformationByProductId(Long productId){
        stockFeignClient.deleteStockInformationByProductId(productId);
    }

    public String getCategoryNameByCategoryId(Product product){
        return categoryFeignClient.findCategoryNameByCategoryId(product.getCategoryId()).getBody();
    }
}
