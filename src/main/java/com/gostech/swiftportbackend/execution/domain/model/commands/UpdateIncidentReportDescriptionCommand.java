package com.gostech.swiftportbackend.execution.domain.model.commands;

public record UpdateIncidentReportDescriptionCommand(Long incidentReportId, String description) {
    public UpdateIncidentReportDescriptionCommand {
        if (incidentReportId == null || incidentReportId <= 0) {
            throw new IllegalArgumentException("Incident Report ID cannot be null or less than 1");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }
}
