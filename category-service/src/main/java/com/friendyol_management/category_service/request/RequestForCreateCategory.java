package com.friendyol_management.category_service.request;

public class RequestForCreateCategory {
    private String name;

    public RequestForCreateCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
