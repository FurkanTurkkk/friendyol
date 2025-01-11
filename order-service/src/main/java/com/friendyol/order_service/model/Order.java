package com.friendyol.order_service.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;
    private BigDecimal totalPrice=BigDecimal.ZERO;

    @OneToMany(mappedBy = "order")
    List<OrderItem> orderItemList=new ArrayList<>();


    public Order() {
    }

    public Order(String userId, List<OrderItem> orderItemList) {
        this.userId = userId;
        this.orderItemList = orderItemList;
    }

    public String getId() {
        return id;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void calculatePrice(){
        List<BigDecimal> prices=orderItemList.stream().map(OrderItem::getPrice).toList();
        this.totalPrice=BigDecimal.ZERO;
        for(BigDecimal price:prices){
            this.totalPrice=totalPrice.add(price);
        }
    }
}
