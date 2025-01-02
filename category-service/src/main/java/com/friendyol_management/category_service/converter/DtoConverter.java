package com.friendyol_management.category_service.converter;

import com.friendyol_management.category_service.model.Category;
import org.example.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public CategoryDto convert(Category category){
        return new CategoryDto(category.getId(),category.getName());
    }
}
