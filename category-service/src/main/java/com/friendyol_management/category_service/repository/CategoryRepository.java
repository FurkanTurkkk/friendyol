package com.friendyol_management.category_service.repository;

import com.friendyol_management.category_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    Optional<Category> findByName(String name);
}
