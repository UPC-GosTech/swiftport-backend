package com.gostech.swiftportbackend.execution.interfaces.rest.resources;

import java.time.LocalDateTime;

public record IncidentReportResource(Long id, String title, String description, LocalDateTime reportedAt, String severity, Long tenantId) {
}
