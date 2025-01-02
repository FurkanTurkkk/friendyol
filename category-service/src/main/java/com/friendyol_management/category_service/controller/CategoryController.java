package com.friendyol_management.category_service.controller;

import com.friendyol_management.category_service.model.Category;
import com.friendyol_management.category_service.request.RequestForCreateCategory;
import com.friendyol_management.category_service.service.CategoryService;
import org.example.CategoryDto;
import org.example.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody RequestForCreateCategory request){
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @GetMapping("/all-product/{categoryId}")
    public ResponseEntity<List<ProductDto>> findListProductByCategoryId(@PathVariable("categoryId")String categoryId){
        return ResponseEntity.ok(categoryService.findProductListByCategoryId(categoryId));
    }

    @GetMapping("/categoryName/{categoryId}")
    public ResponseEntity<String> findCategoryNameByCategoryId(@PathVariable("categoryId") String categoryId){
        return ResponseEntity.ok(categoryService.findCategoryNameByCategoryId(categoryId));
    }

    @GetMapping("/all-categories")
    public List<CategoryDto> findAllCategories(){
        return categoryService.findAllCategories();
    }

}
