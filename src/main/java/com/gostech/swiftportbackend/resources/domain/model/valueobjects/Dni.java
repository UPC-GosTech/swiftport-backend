package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Dni(String dni) {
    public Dni {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("DNI cannot be null or empty");
        }
    }
}
