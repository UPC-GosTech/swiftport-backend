package com.gostech.swiftportbackend.resources.domain.model.queries;

public record CheckResourceAvailabilityByIdQuery(Long resourceId) {
    public CheckResourceAvailabilityByIdQuery {
        if (resourceId == null || resourceId <= 0) {
            throw new IllegalArgumentException("Resource Id is required");
        }
    }
}
