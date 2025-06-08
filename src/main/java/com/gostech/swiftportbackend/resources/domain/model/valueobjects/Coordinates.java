package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Coordinates(Double latitude, Double longitude) {

    public Coordinates {
        if (latitude == null) {
            throw new IllegalArgumentException("Latitude must not be null");
        }
        if (longitude == null) {
            throw new IllegalArgumentException("Longitude must not be null");
        }
    }
}