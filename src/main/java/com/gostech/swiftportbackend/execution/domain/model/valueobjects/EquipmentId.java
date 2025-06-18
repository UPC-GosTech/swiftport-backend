package com.gostech.swiftportbackend.execution.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record EquipmentId(Long equipmentId) {
    public EquipmentId {
        if (equipmentId == null || equipmentId <= 0) {
            throw new IllegalArgumentException("Equipment id cannot be null or empty");
        }
    }
}
