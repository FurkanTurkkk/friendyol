package com.friendyol.order_service.service;

import com.friendyol.order_service.converter.OrderDtoConverter;
import com.friendyol.order_service.model.Order;
import com.friendyol.order_service.model.OrderItem;
import com.friendyol.order_service.repository.OrderRepository;
import com.friendyol.order_service.util.FeignClientService;
import org.example.CartDto;
import org.example.CartItemDto;
import org.example.OrderDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDtoConverter converter;
    private final FeignClientService feignClientService;

    public OrderService(OrderRepository orderRepository,
                        OrderDtoConverter converter,
                        FeignClientService feignClientService) {
        this.orderRepository = orderRepository;
        this.converter = converter;
        this.feignClientService = feignClientService;
    }


    public OrderDto createOrder(String userId) {
        CartDto cartDto=feignClientService.findCartByUserId(userId);
        List<CartItemDto> cartItemDtoList=cartDto.getCartItemDtoList();

        List<OrderItem> orderItemList=cartItemDtoList.stream()
                .map(cartItemDto -> {
                    Long productId= feignClientService.findProductIdByProductName(cartItemDto.getProductName());
                    OrderItem orderItem=new OrderItem(productId,cartItemDto.getQuantity());
                    orderItem.calculatePrice(cartItemDto.getPrice());
                    feignClientService.updateStock(orderItem.getProductId(), (long) orderItem.getQuantity());
                    return orderItem;
                }).toList();
        feignClientService.deleteCart(cartDto.getCartId());
        Order order=new Order(userId,orderItemList);
        order.calculatePrice();
        orderRepository.save(order);
        return converter.convert(order);
    }
}
