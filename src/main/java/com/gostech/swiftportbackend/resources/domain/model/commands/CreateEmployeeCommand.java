package com.gostech.swiftportbackend.resources.domain.model.commands;

public record CreateEmployeeCommand(Long employeeId, Long tenantId, String name, Long positionId, String employeeStatus) {
    public CreateEmployeeCommand {
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("EmployeeId cannot be null");
        }
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("TenantId cannot be null");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (positionId == null || positionId <= 0) {
            throw new IllegalArgumentException("positionId cannot be null");
        }
        if (employeeStatus == null || employeeStatus.isEmpty()) {
            throw new IllegalArgumentException("employeeStatus cannot be null");
        }
    }
}
