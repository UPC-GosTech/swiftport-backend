package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.*;

public record CreateEmployeeCommand(EmployeeId employeeId, TenantId tenantId, String name, PositionId positionId, EmployeeStatus employeeStatus, TimeInterval timeInterval) {

    public CreateEmployeeCommand {
        if (employeeId == null) {
            throw new IllegalArgumentException("EmployeeId cannot be null");
        }
        if (tenantId == null) {
            throw new IllegalArgumentException("TenantId cannot be null");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (positionId == null) {
            throw new IllegalArgumentException("positionId cannot be null");
        }
        if (employeeStatus == null) {
            throw new IllegalArgumentException("employeeStatus cannot be null");
        }
        if (timeInterval == null) {
            throw new IllegalArgumentException("timeInterval cannot be null");
        }
    }
}
