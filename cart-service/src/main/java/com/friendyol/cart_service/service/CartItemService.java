package com.friendyol.cart_service.service;

import com.friendyol.cart_service.converter.CartItemDtoConverter;
import com.friendyol.cart_service.model.Cart;
import com.friendyol.cart_service.model.CartItem;
import com.friendyol.cart_service.repository.CartItemRepository;
import com.friendyol.cart_service.request.RequestForCreateCartItem;
import com.friendyol.cart_service.util.FeignClientService;
import jakarta.transaction.Transactional;
import org.example.CartItemDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemDtoConverter converter;
    private final FeignClientService feignClientService;
    private final CartService cartService;

    public CartItemService(CartItemRepository cartItemRepository,
                           CartItemDtoConverter converter,
                           FeignClientService feignClientService, CartService cartService) {
        this.cartItemRepository = cartItemRepository;
        this.converter = converter;
        this.feignClientService = feignClientService;
        this.cartService = cartService;
    }

    @Transactional
    public CartItemDto createCartItem(RequestForCreateCartItem request) {
        Cart cart=cartService.findCartByCartId(request.getCartId());
        CartItem cartItem=saveCartItem(new CartItem(cart, request.getProductId(), request.getQuantity()));
        cart.getCartItemList().add(cartItem);
        cart.calculatePrice();
        return converter.convert(cartItem);
    }

    private CartItem saveCartItem(CartItem cartItem){
        BigDecimal productPrice=feignClientService.findProductPriceByProductId(cartItem.getProductId());
        Optional<CartItem> registeredCartItem=cartItemRepository.findByCartAndProductId(cartItem.getCart(),cartItem.getProductId());
        if(registeredCartItem.isPresent()){
            CartItem existingCartItem=registeredCartItem.get();
            System.out.println(existingCartItem);
            existingCartItem.updateQuantity(cartItem.getQuantity());
            existingCartItem.updatePrice(productPrice);
            System.out.println("price : "+existingCartItem.getPrice()+" quantity : "+existingCartItem.getQuantity());
            cartItemRepository.save(existingCartItem);
            System.out.println("Existing cart item kaydedildi");
            return existingCartItem;
        }
        CartItem newCartItem=new CartItem(cartItem.getCart(),cartItem.getProductId(),cartItem.getQuantity());
        newCartItem.updatePrice(productPrice);
        System.out.println(newCartItem);
        cartItemRepository.save(newCartItem);
        return newCartItem;
    }
}
