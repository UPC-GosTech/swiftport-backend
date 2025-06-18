package com.gostech.swiftportbackend.execution.domain.model.commands;

public record AddEquipmentIdToExecutionCommand(Long equipmentId, Long executionId) {
    public AddEquipmentIdToExecutionCommand {
        if (equipmentId == null || equipmentId <= 0) {
            throw new IllegalArgumentException("Equipment Id can't be null or empty");
        }
        if (executionId == null || executionId <= 0) {
            throw new IllegalArgumentException("Execution Id can't be null or empty");
        }
    }
}
