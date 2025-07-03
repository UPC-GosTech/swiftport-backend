package com.gostech.swiftportbackend.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record EmployeeId(Long employeeId) {
    public EmployeeId {
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("employeeId can't be null or empty");
        }
    }
}
