package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetEmployeeByIdQuery(Long employeeId) {
    public GetEmployeeByIdQuery {
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("Employee Id is required");
        }
    }
}
