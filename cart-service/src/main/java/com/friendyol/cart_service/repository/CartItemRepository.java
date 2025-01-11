package com.friendyol.cart_service.repository;

import com.friendyol.cart_service.model.Cart;
import com.friendyol.cart_service.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCartAndProductId(Cart cart,Long productId);
}
