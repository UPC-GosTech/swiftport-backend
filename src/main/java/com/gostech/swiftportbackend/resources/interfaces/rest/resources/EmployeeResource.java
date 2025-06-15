package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Availability;

public record EmployeeResource(Long employeeId, Long tenantId, String name, String lastName, String position, Availability employeeStatus, String email, String phoneNumber) {
}
