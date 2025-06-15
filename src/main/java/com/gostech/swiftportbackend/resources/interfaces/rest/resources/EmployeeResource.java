package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

public record EmployeeResource(Long employeeId, Long tenantId, String name, String lastName, String position, String employeeStatus, String email, String phoneNumber) {
}
