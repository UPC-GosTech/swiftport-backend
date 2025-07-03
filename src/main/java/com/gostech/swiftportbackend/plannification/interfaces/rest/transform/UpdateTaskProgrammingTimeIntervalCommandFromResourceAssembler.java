package com.gostech.swiftportbackend.plannification.interfaces.rest.transform;

import com.gostech.swiftportbackend.plannification.domain.model.commands.UpdateTaskProgrammingTimeIntervalCommand;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.UpdateTaskProgrammingTimeIntervalResource;

public class UpdateTaskProgrammingTimeIntervalCommandFromResourceAssembler {
    public static UpdateTaskProgrammingTimeIntervalCommand toCommandFromResource(Long taskProgrammingId, UpdateTaskProgrammingTimeIntervalResource resource) {
        return new UpdateTaskProgrammingTimeIntervalCommand(
                taskProgrammingId,
                resource.start(),
                resource.end()
        );
    }
}
