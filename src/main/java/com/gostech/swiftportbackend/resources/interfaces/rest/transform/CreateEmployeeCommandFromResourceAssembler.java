package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEmployeeCommand;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateEmployeeResource;

public class CreateEmployeeCommandFromResourceAssembler {
    public static CreateEmployeeCommand toCommandFromResource(CreateEmployeeResource resource) {
        Long positionId = null;
        try {
            positionId = resource.positionId();
        } catch (Exception e) {
            throw new IllegalArgumentException("El campo positionId no está disponible en CreateEmployeeResource. Verifica la definición del record.");
        }
        return new CreateEmployeeCommand(
                resource.tenantId(),
                resource.name(),
                resource.lastName(),
                null,
                resource.employeeStatus(),
                resource.email(),
                resource.phoneNumber(),
                positionId
        );
    }
}
