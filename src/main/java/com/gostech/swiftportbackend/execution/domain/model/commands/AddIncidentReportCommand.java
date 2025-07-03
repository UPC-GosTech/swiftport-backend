package com.gostech.swiftportbackend.execution.domain.model.commands;

import java.time.LocalDateTime;

public record AddIncidentReportCommand(Long executionId, String title, String description, LocalDateTime reportedAt, String severity, Long tenantId, Long employeeId) {
    public AddIncidentReportCommand {
        if (executionId == null || executionId <= 0) {
            throw new IllegalArgumentException("Execution id cannot be null or less than 1");
        }
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (reportedAt == null || reportedAt.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Reported at cannot be null or after now");
        }
        if (severity == null || severity.isEmpty()) {
            throw new IllegalArgumentException("Severity cannot be null or empty");
        }
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("Tenant Id cannot be null or less than 1");
        }
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("Employee Id cannot be null or less than 1");
        }
    }
}
