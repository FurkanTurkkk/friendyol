package com.friendyol.cart_service.repository;

import com.friendyol.cart_service.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUserId(String userId);
}
