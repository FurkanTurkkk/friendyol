package org.example;

public class CategoryDto {

    private String categoryId;
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(String categoryId, String name){
        this.categoryId=categoryId;
        this.name=name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
