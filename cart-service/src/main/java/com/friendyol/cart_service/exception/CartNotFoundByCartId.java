package com.friendyol.cart_service.exception;

public class CartNotFoundByCartId extends RuntimeException{
    public CartNotFoundByCartId(String s) {
        super(s);
    }
}
