package com.gostech.swiftportbackend.resources.domain.model.commands;

public record CreateEquipmentCommand(Long equipmentId, Long tenantId, String name, String status) {
    public  CreateEquipmentCommand {
        if (equipmentId == null || equipmentId <= 0) {
            throw new IllegalArgumentException("equipmentId cannot be null");
        }
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("tenantId cannot be null");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("status cannot be null");
        }
    }
}
