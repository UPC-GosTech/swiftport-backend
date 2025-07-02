package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

public record UpdateEmployeeIdResource(Long employeeId) {
    public UpdateEmployeeIdResource {
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("Employee id cannot be null or zero or negative");
        }
    }
}
