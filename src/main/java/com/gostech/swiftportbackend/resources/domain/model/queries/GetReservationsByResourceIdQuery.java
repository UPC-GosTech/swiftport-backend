package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetReservationsByResourceIdQuery(Long resourceId) {
    public GetReservationsByResourceIdQuery {
        if (resourceId == null || resourceId <= 0) {
            throw new IllegalArgumentException("ResourceId is required");
        }
    }
}
