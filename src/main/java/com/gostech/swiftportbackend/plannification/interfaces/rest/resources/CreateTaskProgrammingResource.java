package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateTaskProgrammingResource(Long taskId, String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end, String programmingStatus) {
    public CreateTaskProgrammingResource {
        if (taskId == null || taskId <= 0) {
            throw new IllegalArgumentException("Task id cannot be null or less than zero");
        }
        if (resourceType == null || resourceType.isEmpty()) {
            throw new IllegalArgumentException("Resource type cannot be null or empty");
        }
        if (resourceId == null || resourceId <= 0) {
            throw new IllegalArgumentException("Resource Id cannot be null or less than zero");
        }
        if (start == null || start.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start date cannot be null or before now");
        }
        if (end == null || end.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("End date cannot be null or before now");
        }
        if (programmingStatus == null || programmingStatus.isEmpty()) {
            throw new IllegalArgumentException("Programming status cannot be null or empty");
        }
    }
}
