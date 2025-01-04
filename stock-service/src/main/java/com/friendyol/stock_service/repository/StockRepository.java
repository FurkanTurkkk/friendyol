package com.friendyol.stock_service.repository;

import com.friendyol.stock_service.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock,String> {
    Optional<Stock> findByProductId(Long productId);
}
