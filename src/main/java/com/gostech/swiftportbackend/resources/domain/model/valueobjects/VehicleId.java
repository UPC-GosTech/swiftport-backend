package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record VehicleId(Long VehicleId) {
    public VehicleId {
        if (VehicleId == null || VehicleId <= 1) {
            throw new NullPointerException("VehicleId cannot be null or less than 1");
        }
    }
}
