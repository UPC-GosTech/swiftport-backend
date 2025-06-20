package com.gostech.swiftportbackend.execution.domain.model.commands;

public record UpdateTaskExecutionStatusCommand(Long executionId, String taskExecutionStatus) {
    public UpdateTaskExecutionStatusCommand {
        if (executionId == null || executionId <= 0) {
            throw new IllegalArgumentException("executionId can't be null");
        }
        if (taskExecutionStatus == null || taskExecutionStatus.isEmpty()) {
            throw new IllegalArgumentException("Task Execution Status can't be null");
        }
    }
}
