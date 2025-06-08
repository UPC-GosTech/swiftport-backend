package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.entities.Location;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TenantId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.ZoneId;

import java.time.LocalDateTime;
import java.util.List;

public record CreateZoneCommand(ZoneId zoneId, TenantId tenantId, String name, List<Location> locations, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public CreateZoneCommand {
        if (zoneId == null) {
            throw new IllegalArgumentException("ZoneId cannot be null");
        }
        if (tenantId == null) {
            throw new IllegalArgumentException("TenantId cannot be null");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (locations == null || locations.isEmpty()) {
            throw new IllegalArgumentException("Locations cannot be null or empty");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt cannot be null");
        }
        if (updatedAt == null) {
            throw new IllegalArgumentException("updatedAt cannot be null");
        }
    }
}
