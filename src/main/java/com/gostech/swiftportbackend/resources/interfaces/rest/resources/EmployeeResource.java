package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Availability;

public record EmployeeResource(
    Long tenantId,
    String name,
    String lastName,
    Long positionId,
    String positionTitle,
    Availability employeeStatus,
    String email,
    String phoneNumber
) {}
