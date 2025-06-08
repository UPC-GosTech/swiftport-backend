package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embedded;

@Embedded
public record LocationId(Long locationId) {
    public LocationId {
        if (locationId == null || locationId < 1) {
            throw new IllegalArgumentException("LocationId cannot be null or less than 1");
        }
    }
}
