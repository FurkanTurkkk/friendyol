package com.friendyol.order_service.converter;

import com.friendyol.order_service.model.Order;
import com.friendyol.order_service.model.OrderItem;
import com.friendyol.order_service.util.FeignClientService;
import org.example.CartDto;
import org.example.OrderDto;
import org.example.OrderItemDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDtoConverter {

    private final OrderItemDtoConverter converter;

    public OrderDtoConverter(OrderItemDtoConverter converter) {
        this.converter = converter;
    }

    public OrderDto convert(Order order){
        List<OrderItemDto> orderItems=order.getOrderItemList()
                .stream()
                .map(converter::convert)
                .toList();
        return new OrderDto(
                order.getId(),
                orderItems,
                order.getTotalPrice()
        );
    }
}
