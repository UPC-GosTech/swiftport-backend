package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetZoneByIdQuery(Long zoneId) {
    public GetZoneByIdQuery {
        if (zoneId == null || zoneId <= 0) {
            throw new IllegalArgumentException("Zone Id is required");
        }
    }
}
