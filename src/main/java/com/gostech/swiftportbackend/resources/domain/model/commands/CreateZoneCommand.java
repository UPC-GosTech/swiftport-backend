package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.entities.Location;

import java.util.List;

public record CreateZoneCommand(Long tenantId, String name, List<Location> locations) {
    public CreateZoneCommand {
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
