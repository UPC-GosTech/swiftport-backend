package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.entities.Location;

import java.util.List;

public record CreateZoneCommand(Long tenantId, String name) {
    public CreateZoneCommand {
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("TenantId cannot be null");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }
}
