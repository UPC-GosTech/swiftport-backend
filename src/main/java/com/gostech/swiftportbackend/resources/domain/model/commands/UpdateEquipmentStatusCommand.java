package com.gostech.swiftportbackend.resources.domain.model.commands;

public record UpdateEquipmentStatusCommand(Long equipmentId, String status) {
    public UpdateEquipmentStatusCommand {
        if (equipmentId == null || equipmentId <= 0) {
            throw new IllegalArgumentException("Equipment ID cannot be null or less than zero");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}
