package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateEmployeeResource;

public class CreateEmployeeCommandFromResourceAssembler {
    public static CreateEmployeeResource toCommandResourceFromEntity(CreateEmployeeResource resource) {
        return new CreateEmployeeResource(
                resource.tenantId(),
                resource.name(),
                resource.lastName(),
                resource.position(),
                resource.employeeStatus(),
                resource.email(),
                resource.phoneNumber());
    }
}
