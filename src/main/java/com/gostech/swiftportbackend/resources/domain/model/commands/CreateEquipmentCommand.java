package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.EquipmentId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.EquipmentStatus;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TenantId;

import java.time.LocalDateTime;

public record CreateEquipmentCommand(EquipmentId equipmentId, TenantId tenantId, String name, EquipmentStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public  CreateEquipmentCommand {
        if (equipmentId == null) {
            throw new IllegalArgumentException("equipmentId cannot be null");
        }
        if (tenantId == null) {
            throw new IllegalArgumentException("tenantId cannot be null");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (status == null) {
            throw new IllegalArgumentException("status cannot be null");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt cannot be null");
        }
        if (updatedAt == null) {
            throw new IllegalArgumentException("updatedAt cannot be null");
        }
    }
}
