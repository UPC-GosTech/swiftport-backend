package com.gostech.swiftportbackend.resources.domain.model.commands;

public record CreateEmployeeCommand(Long employeeId, Long tenantId, String name, String lastName, String position, String employeeStatus, String email, String phoneNumber) {
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
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("lastName cannot be null or empty");
        }
        if (position == null || position.isEmpty()) {
            throw new IllegalArgumentException("positionId cannot be null  or empty");
        }
        if (employeeStatus == null || employeeStatus.isEmpty()) {
            throw new IllegalArgumentException("employeeStatus cannot be null");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("phoneNumber cannot be null or empty");
        }
    }
}
