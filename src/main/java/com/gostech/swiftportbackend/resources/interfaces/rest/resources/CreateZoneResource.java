package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

import com.gostech.swiftportbackend.resources.domain.model.entities.Location;

import java.util.List;

public record CreateZoneResource(Long tenantId, String name, List<Location> locations) {
    public CreateZoneResource {
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("TenantId cannot be null");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (locations == null || locations.isEmpty()) {
            throw new IllegalArgumentException("Locations cannot be null or empty");
        }
    }
}
