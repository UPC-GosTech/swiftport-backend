package com.gostech.swiftportbackend.execution.interfaces.rest.resources;

public record UpdateTaskExecutionStatusResource(String taskExecutionStatus) {
    public UpdateTaskExecutionStatusResource {
        if (taskExecutionStatus == null || taskExecutionStatus.isEmpty()) {
            throw new IllegalArgumentException("Task Execution Status can't be null");
        }
    }
}
