package com.gostech.swiftportbackend.plannification.interfaces.rest.transform;

import com.gostech.swiftportbackend.plannification.domain.model.commands.UpdateEmployeeAssignedOnTaskCommand;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.UpdateEmployeeIdResource;

public class UpdateTaskEmployeeIdCommandFromResourceAssembler {
    public static UpdateEmployeeAssignedOnTaskCommand toCommandFromResource(Long taskId, UpdateEmployeeIdResource resource) {
        return new UpdateEmployeeAssignedOnTaskCommand(taskId, resource.employeeId());
    }
}
