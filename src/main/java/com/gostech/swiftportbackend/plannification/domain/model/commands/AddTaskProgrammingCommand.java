package com.gostech.swiftportbackend.plannification.domain.model.commands;

import java.time.LocalDateTime;

public record AddTaskProgrammingCommand(Long taskId, String resourceType, Long resourceId, String programmingStatus, LocalDateTime start, LocalDateTime end) {
    public AddTaskProgrammingCommand {
        if (taskId == null || taskId <= 0) {
            throw new IllegalArgumentException("Task id cannot be null or less than zero");
        }
        if (resourceType == null || resourceType.isEmpty()) {
            throw new IllegalArgumentException("Resource type cannot be null or empty");
        }
        if (resourceId == null || resourceId <= 0) {
            throw new IllegalArgumentException("Resource Id cannot be null or less than zero");
        }
        if (programmingStatus == null || programmingStatus.isEmpty()) {
            throw new IllegalArgumentException("Programming status cannot be null or empty");
        }
        if (start == null || start.isAfter(end)) {
            throw new IllegalArgumentException("Start date cannot be null or after end date");
        }
        if (end == null || end.isBefore(start)) {
            throw new IllegalArgumentException("End date cannot be null or before start date");
        }
    }
}
