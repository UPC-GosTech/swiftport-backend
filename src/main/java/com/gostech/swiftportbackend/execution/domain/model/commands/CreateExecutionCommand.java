package com.gostech.swiftportbackend.execution.domain.model.commands;

import java.time.LocalDateTime;

public record CreateExecutionCommand(Long taskProgrammingId, String taskExecutionStatus, LocalDateTime start, LocalDateTime end, String modificationReason, Long tenantId) {
    public CreateExecutionCommand {
        if (taskProgrammingId == null || taskProgrammingId <= 0) {
            throw new IllegalArgumentException("Task Programming Id can't be null or empty");
        }
        if (taskExecutionStatus == null || taskExecutionStatus.isEmpty()) {
            throw new IllegalArgumentException("Task Execution Status can't be null or empty");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start date can't be null or empty");
        }
        if (end == null || end.isBefore(start)) {
            throw new IllegalArgumentException("End date can't be null or before start date");
        }
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("Tenant Id can't be null or empty");
        }
    }
}
