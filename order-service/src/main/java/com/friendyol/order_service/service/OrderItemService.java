package com.friendyol.order_service.service;

import com.friendyol.order_service.model.OrderItem;
import com.friendyol.order_service.repository.OrderItemRepository;
import com.friendyol.order_service.util.FeignClientService;
import org.example.CartDto;
import org.example.CartItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final FeignClientService feignClientService;


    public OrderItemService(OrderItemRepository orderItemRepository, FeignClientService feignClientService) {
        this.orderItemRepository = orderItemRepository;
        this.feignClientService = feignClientService;
    }


}
