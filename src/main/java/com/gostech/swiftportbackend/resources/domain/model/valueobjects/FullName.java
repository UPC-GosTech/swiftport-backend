package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record FullName(String firstName, String lastName) {
    public FullName {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("firstName cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("lastName cannot be null or empty");
        }
    }
}
