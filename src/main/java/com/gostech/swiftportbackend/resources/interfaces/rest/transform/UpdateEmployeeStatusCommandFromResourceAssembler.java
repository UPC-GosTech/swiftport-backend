package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.commands.UpdateEmployeeStatusCommand;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.UpdateEmployeeStatusResource;

public class UpdateEmployeeStatusCommandFromResourceAssembler {
    public static UpdateEmployeeStatusCommand toCommandFromResource(Long employeeId, UpdateEmployeeStatusResource resource) {
        return new UpdateEmployeeStatusCommand(
                employeeId,
                resource.status()
        );
    }
}
