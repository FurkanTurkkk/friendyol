package com.friendyol_management.category_service.service;

import com.friendyol_management.category_service.converter.DtoConverter;
import com.friendyol_management.category_service.feignclient.ProductFeignClient;
import com.friendyol_management.category_service.model.Category;
import com.friendyol_management.category_service.repository.CategoryRepository;
import com.friendyol_management.category_service.request.RequestForCreateCategory;
import org.example.CategoryDto;
import org.example.ProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final DtoConverter converter;
    private final ProductFeignClient productFeignClient;

    public CategoryService(CategoryRepository categoryRepository,
                           DtoConverter converter,
                           ProductFeignClient productFeignClient) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
        this.productFeignClient = productFeignClient;
    }

    public CategoryDto createCategory(RequestForCreateCategory request){
        Category category=new Category(request.getName());
        saveCategory(category);
        return converter.convert(category);
    }

    public String findCategoryNameByCategoryId(String categoryId){
        return findCategoryByCategoryId(categoryId).getName();
    }

    public List<ProductDto> findProductListByCategoryId(String categoryId){
        System.out.println("findProductListByCategoryId deyim");
        return productFeignClient.findProductListByCategoryId(categoryId).getBody();
    }

    public CategoryDto findCategoryByCategoryId(String id){
        Category category=findCategoryById(id);
        return converter.convert(category);
    }

    public List<CategoryDto> findAllCategories(){
        List<Category> categories=getAllCategoryList();
        return categories.stream().map(converter::convert).toList();
    }

    private List<Category> getAllCategoryList(){
        return categoryRepository.findAll();
    }

    private Category findCategoryById(String id){
        return categoryRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Kategory bulunamadı"));
    }

    private void saveCategory(Category category){
        if (categoryRepository.findByName(category.getName()).isPresent()){
            throw new IllegalArgumentException("Bu isimde kategori mevcut güncellemek için güncelleme sayfasına gidin");
        }
        categoryRepository.save(category);
    }
}
