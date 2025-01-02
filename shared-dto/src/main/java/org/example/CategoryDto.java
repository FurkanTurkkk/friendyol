package org.example;


public class CategoryDto {

    private String categoryId;
    private String name;

    public CategoryDto(String categoryId,String name) {
        this.categoryId=categoryId;
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }


    public String getName() {
        return name;
    }
}
