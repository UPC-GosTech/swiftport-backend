package com.gostech.swiftportbackend.execution.domain.model.commands;

public record UpdateIncidentReportEmployeeIdCommand(Long incidentReportId, Long employeeId) {
    public UpdateIncidentReportEmployeeIdCommand {
        if (incidentReportId == null || incidentReportId <= 0) {
            throw new IllegalArgumentException("Incident report id cannot be null or empty");
        }
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("Employee id cannot be null or empty");
        }
    }
}
