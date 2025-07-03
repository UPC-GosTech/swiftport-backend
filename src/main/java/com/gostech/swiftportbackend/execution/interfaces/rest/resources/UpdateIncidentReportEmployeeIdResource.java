package com.gostech.swiftportbackend.execution.interfaces.rest.resources;

public record UpdateIncidentReportEmployeeIdResource(Long employeeId) {
    public UpdateIncidentReportEmployeeIdResource {
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("Employee id cannot be null or empty");
        }
    }
}
