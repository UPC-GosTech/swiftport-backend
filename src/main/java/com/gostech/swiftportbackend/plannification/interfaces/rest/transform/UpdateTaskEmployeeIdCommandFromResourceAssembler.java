package com.gostech.swiftportbackend.plannification.interfaces.rest.transform;

import com.gostech.swiftportbackend.plannification.domain.model.commands.UpdateEmployeeAssignedOnTaskCommand;

public class UpdateTaskEmployeeIdCommandFromResourceAssembler {
    public static UpdateEmployeeAssignedOnTaskCommand toCommandFromResource(Long taskId, Long employeeId) {
        return new UpdateEmployeeAssignedOnTaskCommand(taskId, employeeId);
    }
}
