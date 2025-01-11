package com.friendyol.order_service.converter;

import com.friendyol.order_service.model.OrderItem;
import com.friendyol.order_service.util.FeignClientService;
import org.example.OrderItemDto;
import org.springframework.stereotype.Component;

@Component
public class OrderItemDtoConverter {

    private final FeignClientService feignClientService;

    public OrderItemDtoConverter(FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    public OrderItemDto convert(OrderItem orderItem){
        String productName= feignClientService.findProductNameByProductId(orderItem.getProductId());
        return new OrderItemDto(
                productName,
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}
