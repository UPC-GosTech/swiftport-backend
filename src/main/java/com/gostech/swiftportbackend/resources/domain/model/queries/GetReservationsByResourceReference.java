package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetReservationsByResourceReference(Long resourceId, String resourceType) {
    public GetReservationsByResourceReference {
        if (resourceId == null || resourceId <= 0) {
            throw new IllegalArgumentException("ResourceId is required");
        }
        if (resourceType == null || resourceType.isEmpty()) {
            throw new IllegalArgumentException("ResourceType is required");
        }
    }
}
