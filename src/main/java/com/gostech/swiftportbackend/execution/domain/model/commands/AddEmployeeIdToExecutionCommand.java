package com.gostech.swiftportbackend.execution.domain.model.commands;

public record AddEmployeeIdToExecutionCommand(Long executionId, Long employeeId) {
    public AddEmployeeIdToExecutionCommand {
        if (executionId == null || executionId <= 0) {
            throw new IllegalArgumentException("Execution Id can't be null or empty");
        }
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("Employee Id can't be null or empty");
        }
    }
}
