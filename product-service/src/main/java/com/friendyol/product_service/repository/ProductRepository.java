package com.friendyol.product_service.repository;

import com.friendyol.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByNameAndColor(String name,String color);
    List<Product> findByCategoryId(String id);
    Optional<Product> findByName(String productName);
}
