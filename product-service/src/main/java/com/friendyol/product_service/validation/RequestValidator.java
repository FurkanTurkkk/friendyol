package com.friendyol.product_service.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequestValidator implements ConstraintValidator<ValidName,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        if (value.matches(".*[.,:;].*")) {
            return false;
        }
        String[] parts = value.trim().split("\\s+");
        return parts.length <= 1;
    }
}
