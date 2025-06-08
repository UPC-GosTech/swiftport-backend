package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record EquipmentId(Long equipmentId) {
    public EquipmentId {
        if (equipmentId == null || equipmentId <= 1) {
            throw new IllegalArgumentException("Equipment Id cannot be null or less than 1");
        }
    }
}
