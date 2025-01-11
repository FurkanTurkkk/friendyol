package com.friendyol.cart_service.exceptionhandler;

import com.friendyol.cart_service.exception.CartNotFoundByCartId;
import com.friendyol.cart_service.exception.CartNotFoundByUserId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handeIllegalArgumentException(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartNotFoundByCartId.class)
    public ResponseEntity<String> handleCartNotFoundByCartId(CartNotFoundByCartId e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartNotFoundByUserId.class)
    public ResponseEntity<String> handleCartNotFoundByUserId(CartNotFoundByUserId e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}
