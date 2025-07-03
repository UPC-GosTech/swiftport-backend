package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

public record CreateEmployeeResource(Long tenantId, String name, String lastName, Long positionId, String employeeStatus, String email, String phoneNumber) {
    public CreateEmployeeResource {
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("TenantId cannot be null");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("lastName cannot be null or empty");
        }
        if (positionId == null || positionId <= 0) {
            throw new IllegalArgumentException("positionId cannot be null or <= 0");
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

    public Long positionId() {
        return this.positionId;
    }
}
