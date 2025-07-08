package com.gostech.swiftportbackend.execution.domain.exceptions;

public class IncidentReportNotSavedException extends RuntimeException {
    public IncidentReportNotSavedException(String message) {
        super("Error saving incident report: " + message);
    }
}
