package com.gostech.swiftportbackend.plannification.domain.model.commands;

import java.time.LocalDateTime;

public record UpdateTaskProgrammingTimeIntervalCommand(Long taskProgrammingId, LocalDateTime start, LocalDateTime end) {
    public UpdateTaskProgrammingTimeIntervalCommand {
        if (taskProgrammingId == null || taskProgrammingId <= 0) {
            throw new IllegalArgumentException("taskProgrammingId cannot be null or blank");
        }
        if (start == null || start.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start date cannot be null or before now");
        }
        if (end == null || end.isBefore(start)) {
            throw new IllegalArgumentException("End date cannot be null or before start date");
        }
    }
}
