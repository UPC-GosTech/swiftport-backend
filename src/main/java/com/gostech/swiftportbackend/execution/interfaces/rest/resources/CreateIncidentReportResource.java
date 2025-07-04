package com.gostech.swiftportbackend.execution.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateIncidentReportResource(String title, String description, LocalDateTime reportedAt, String severity, Long employeeId) {
    public CreateIncidentReportResource {
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
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("Employee Id cannot be null or less than 1");
        }
    }
}
