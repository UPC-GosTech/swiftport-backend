package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetLocationsByZoneIdQuery(Long zoneId) {
    public GetLocationsByZoneIdQuery {
        if (zoneId == null || zoneId <= 0) {
            throw new IllegalArgumentException("Invalid zoneId");
        }
    }
}
