package com.friendyol.cart_service.exception;

public class CartNotFoundByUserId extends RuntimeException {
    public CartNotFoundByUserId(String s) {
        super(s);
    }
}
