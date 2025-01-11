package com.friendyol.cart_service.service;

import com.friendyol.cart_service.converter.CartDtoConverter;
import com.friendyol.cart_service.exception.CartNotFoundByCartId;
import com.friendyol.cart_service.exception.CartNotFoundByUserId;
import com.friendyol.cart_service.model.Cart;
import com.friendyol.cart_service.repository.CartRepository;
import com.friendyol.cart_service.request.RequestForCreateCart;
import org.example.CartDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartDtoConverter converter;

    public CartService(CartRepository cartRepository, CartDtoConverter converter) {
        this.cartRepository = cartRepository;
        this.converter = converter;
    }

    public CartDto createCart(RequestForCreateCart request) {
        Cart cart=saveCart(new Cart(request.getUserId()));
        return converter.convert(cart);
    }

    public CartDto getCartByCartId(Long cartId){
        return converter.convert(findCartByCartId(cartId));
    }

    public Cart findCartByCartId(Long cartId){
        return cartRepository.findById(cartId)
                .orElseThrow(()->new CartNotFoundByCartId("Cart could not found by id : "+cartId));
    }

    public void deleteCartByCartId(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public CartDto findCartByUserId(String userId) {
        Cart cart=cartRepository.findByUserId(userId)
                .orElseThrow(()->new CartNotFoundByUserId("Cart could not found by user id : "+userId));
        return converter.convert(cart);
    }

    private Cart saveCart(Cart cart){
        Optional<Cart> registeredCart=cartRepository.findByUserId(cart.getUserId());
        if(registeredCart.isPresent()){
            throw new IllegalArgumentException("Cart already exist for this user");
        }
        return cartRepository.save(cart);
    }


}
