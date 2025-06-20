package com.gostech.swiftportbackend.execution.interfaces.rest.resources;

public record UpdateTaskExecutionStatusResource(Long executionId, String taskExecutionStatus) {
    public UpdateTaskExecutionStatusResource {
        if (executionId == null || executionId <= 0) {
            throw new IllegalArgumentException("executionId can't be null");
        }
        if (taskExecutionStatus == null || taskExecutionStatus.isEmpty()) {
            throw new IllegalArgumentException("Task Execution Status can't be null");
        }
    }
}
