package com.gostech.swiftportbackend.execution.interfaces.rest.resources;

import com.gostech.swiftportbackend.execution.domain.model.valueobjects.IncidentSeverity;

import java.time.LocalDateTime;

public record IncidentReportResource(Long id, Long executionId, String title, String description, LocalDateTime reportedAt, IncidentSeverity severity, Long tenantId) {
}
