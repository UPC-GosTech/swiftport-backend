package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetEquipmentByIdQuery(Long equipmentId) {
    public GetEquipmentByIdQuery {
        if (equipmentId == null || equipmentId <= 0) {
            throw new IllegalArgumentException("Equipment Id is required");
        }
    }
}
