package com.gostech.swiftportbackend.execution.interfaces.rest.resources;

public record UpdateIncidentReportResource(Long incidentReportId, String description) {
    public UpdateIncidentReportResource {
        if (incidentReportId == null || incidentReportId <= 0) {
            throw new IllegalArgumentException("Incident Report ID cannot be null or less than 1");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }
}
