package com.gostech.swiftportbackend.plannification.interfaces.rest.transform;

import com.gostech.swiftportbackend.plannification.domain.model.commands.UpdateTaskProgrammingStatusCommand;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.UpdateTaskProgrammingStatusResource;

public class UpdateTaskProgrammingStatusCommandFromResourceAssembler {
    public static UpdateTaskProgrammingStatusCommand toCommandFromResource(Long taskProgrammingId, UpdateTaskProgrammingStatusResource resource) {
        return new UpdateTaskProgrammingStatusCommand(
                taskProgrammingId,
                resource.programmingStatus()
        );
    }
}
