package com.gostech.swiftportbackend.execution.domain.exceptions;

public class IncidentReportNotFoundExceptions extends RuntimeException {
    public IncidentReportNotFoundExceptions(Long incidentReportId) {
        super("Incident report not found by id: " + incidentReportId);
    }
}
