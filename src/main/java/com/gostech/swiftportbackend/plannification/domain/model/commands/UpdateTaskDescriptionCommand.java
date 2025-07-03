package com.gostech.swiftportbackend.plannification.domain.model.commands;

public record UpdateTaskDescriptionCommand (Long taskId, String description) {
    public UpdateTaskDescriptionCommand {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (taskId == null ||  taskId == 0) {
            throw new IllegalArgumentException("TaskId cannot be null or empty");
        }
    }
}
