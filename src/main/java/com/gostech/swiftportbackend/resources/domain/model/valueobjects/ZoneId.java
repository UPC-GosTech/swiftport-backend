package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ZoneId(Long zoneId) {
    public ZoneId {
        if (zoneId == null || zoneId < 1) {
            throw new IllegalArgumentException("Zone Id cannot be null or less than 1");
        }
    }
}
