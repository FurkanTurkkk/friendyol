package com.friendyol.cart_service.request;

public class RequestForCreateCart {

    private String userId;

    public RequestForCreateCart(){

    }

    public RequestForCreateCart(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
