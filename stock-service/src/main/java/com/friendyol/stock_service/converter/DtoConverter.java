package com.friendyol.stock_service.converter;

import com.friendyol.stock_service.feignclient.ProductFeignClient;
import com.friendyol.stock_service.model.Stock;
import org.example.StockDto;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    private final ProductFeignClient productFeignClient;

    public DtoConverter(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    public StockDto convertToStockDto(Stock stock){
        String productName=productFeignClient.findProductNameByProductId(stock.getProductId()).getBody();
        return new StockDto(
                productName,
                stock.getQuantity()
        );
    }

}
