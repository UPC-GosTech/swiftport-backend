package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetEquipmentsByStatusQuery(String status) {
    public GetEquipmentsByStatusQuery {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("status cannot be null or empty");
        }
    }
}
