package org.example;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    private String orderId;
    private List<OrderItemDto> orderItemDtoList;
    private BigDecimal totalPrice;

    public OrderDto() {
    }

    public OrderDto(String orderId, List<OrderItemDto> orderItemDtoList, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.orderItemDtoList = orderItemDtoList;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<OrderItemDto> getOrderItemDtoList() {
        return orderItemDtoList;
    }

    public void setOrderItemDtoList(List<OrderItemDto> orderItemDtoList) {
        this.orderItemDtoList = orderItemDtoList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
