package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetLocationsByZoneId(Long zoneId) {
    public GetLocationsByZoneId {
        if (zoneId == null || zoneId <= 0) {
            throw new IllegalArgumentException("Invalid zoneId");
        }
    }
}
