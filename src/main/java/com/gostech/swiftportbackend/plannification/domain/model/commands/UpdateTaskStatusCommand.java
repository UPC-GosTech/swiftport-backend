package com.gostech.swiftportbackend.plannification.domain.model.commands;

public record UpdateTaskStatusCommand(Long taskId, String status) {
    public UpdateTaskStatusCommand {
        if (taskId == null ||  taskId == 0) {
            throw new IllegalArgumentException("TaskId cannot be null or empty");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}
