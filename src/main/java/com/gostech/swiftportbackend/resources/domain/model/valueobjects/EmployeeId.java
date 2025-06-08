package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record EmployeeId(Long employeeId) {
    public EmployeeId {
        if (employeeId == null || employeeId <= 1) {
            throw new IllegalArgumentException("EmployeeId cannot be null or less than 1");
        }
    }
}
