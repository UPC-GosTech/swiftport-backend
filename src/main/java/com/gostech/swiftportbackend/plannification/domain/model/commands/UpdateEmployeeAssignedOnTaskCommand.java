package com.gostech.swiftportbackend.plannification.domain.model.commands;

public record UpdateEmployeeAssignedOnTaskCommand(Long employeeId, Long taskId) {
    public UpdateEmployeeAssignedOnTaskCommand {
        if (taskId == null || taskId <= 0) {
            throw  new IllegalArgumentException(" Task id cannot be null or empty");
        }
        if (employeeId == null || employeeId <= 0) {
            throw  new IllegalArgumentException(" Employee id cannot be null or empty");
        }
    }
}
