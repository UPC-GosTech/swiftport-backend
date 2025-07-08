package com.gostech.swiftportbackend.execution.domain.exceptions;

public class IncidentReportTitleAlreadyExistsException extends RuntimeException {
    public IncidentReportTitleAlreadyExistsException(String message) {
        super("Incident report title already exists: " + message);
    }
}
