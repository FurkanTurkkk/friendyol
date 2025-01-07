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

    @Transactional
    public void deleteCartItemByCartItemId(Long cartItemId) {
        CartItem cartItem=cartItemRepository.findById(cartItemId)
                .orElseThrow(()->new IllegalArgumentException("Cart item could not found by id : "+cartItemId));
        cartItem.updateQuantity(0);
        cartItem.updatePrice(BigDecimal.ZERO);
        Cart cart=cartItem.getCart();
        cart.getCartItemList().remove(cartItem);
        cart.calculatePrice();
        cartItemRepository.delete(cartItem);
    }

    private CartItem saveCartItem(CartItem cartItem){
        CartItem savedCartItem=findCartItemByCartAndProductId(cartItem);
        return cartItemRepository.save(savedCartItem);
    }

    private CartItem findCartItemByCartAndProductId(CartItem cartItem){
        Optional<CartItem> registeredCartItem=cartItemRepository.findByCartAndProductId(cartItem.getCart()
                ,cartItem.getProductId());
        if(registeredCartItem.isPresent()){
            CartItem existingCartItem=registeredCartItem.get();
            updateCartItemQuantity(existingCartItem,cartItem.getQuantity());
            updateCartItemPrice(existingCartItem);
            return existingCartItem;
        }
        CartItem createdCartItem=new CartItem(cartItem.getCart(),cartItem.getProductId(), cartItem.getQuantity());
        updateCartItemPrice(createdCartItem);
        return createdCartItem;
    }

    private void updateCartItemQuantity(CartItem cartItem,int quantity){
        cartItem.updateQuantity(quantity);
    }

    private void updateCartItemPrice(CartItem cartItem){
        BigDecimal price=feignClientService.findProductPriceByProductId(cartItem.getProductId());
        cartItem.updatePrice(price);
    }

}
