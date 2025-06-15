package com.gostech.swiftportbackend.plannification.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record LocationRef(Long zoneId, Long locationId) {
    public LocationRef {
        if (zoneId == null || zoneId <= 0) {
            throw new IllegalArgumentException("Zone id cannot be null or zero or negative");
        }
        if (locationId == null || locationId <= 0) {
            throw new IllegalArgumentException("Location id cannot be null or zero or negative");
        }
    }
}
