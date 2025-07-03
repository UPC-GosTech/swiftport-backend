package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

import java.time.LocalDateTime;

public record UpdateTaskProgrammingTimeIntervalResource(LocalDateTime start, LocalDateTime end) {
    public UpdateTaskProgrammingTimeIntervalResource {
        if (start == null || start.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start date cannot be null or before now");
        }
        if (end == null || end.isBefore(start)) {
            throw new IllegalArgumentException("End date cannot be null or before start date");
        }
    }
}
