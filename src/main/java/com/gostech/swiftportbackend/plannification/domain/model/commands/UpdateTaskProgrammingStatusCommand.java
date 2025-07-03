package com.gostech.swiftportbackend.plannification.domain.model.commands;

public record UpdateTaskProgrammingStatusCommand(Long taskProgrammingId, String programmingStatus) {
    public UpdateTaskProgrammingStatusCommand {
        if (taskProgrammingId == null || taskProgrammingId <= 0) {
            throw new IllegalArgumentException("taskProgrammingId cannot be null or blank");
        }
        if (programmingStatus == null || programmingStatus.isEmpty()) {
            throw new IllegalArgumentException("Programming status cannot be null or empty");
        }
    }
}
