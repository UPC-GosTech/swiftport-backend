package com.gostech.swiftportbackend.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Value Object para representar una dirección de email
 */
@Embeddable
public record EmailAddress(@Column(name = "email") String value) {
    
    public EmailAddress {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!isValidEmail(value)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
    
    private static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".") && email.length() > 5;
    }
    
    @Override
    public String toString() {
        return value;
    }
} 