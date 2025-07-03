package com.gostech.swiftportbackend.resources.domain.model.commands;

public record UpdateEmployeeStatusCommand(Long employeeId, String status) {
    public UpdateEmployeeStatusCommand {
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("Employee ID cannot be null or zero or negative");
        }
    }
}
