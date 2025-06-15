package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ContactInfo(String email, String phoneNumber) {
    public ContactInfo {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("phoneNumber cannot be null or empty");
        }
    }
}
