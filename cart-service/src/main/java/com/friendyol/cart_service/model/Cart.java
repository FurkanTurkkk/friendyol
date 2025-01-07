package com.friendyol.cart_service.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private BigDecimal price=BigDecimal.ZERO;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    List<CartItem> cartItemList=new ArrayList<>();

    public Cart() {
    }

    public Cart(String userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void calculatePrice(){
        List<BigDecimal> totalPrice=this.cartItemList.stream().map(CartItem::getPrice).toList();
        this.price=BigDecimal.ZERO;
        for (BigDecimal price:totalPrice){
            this.price=this.price.add(price);
        }
    }

}
