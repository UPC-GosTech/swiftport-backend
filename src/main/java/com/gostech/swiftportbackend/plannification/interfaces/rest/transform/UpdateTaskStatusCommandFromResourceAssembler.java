package com.gostech.swiftportbackend.plannification.interfaces.rest.transform;

import com.gostech.swiftportbackend.plannification.domain.model.commands.UpdateTaskStatusCommand;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.UpdateTaskStatusResource;

public class UpdateTaskStatusCommandFromResourceAssembler {
    public static UpdateTaskStatusCommand toCommandFromResource(Long taskId, UpdateTaskStatusResource resource) {
        return new UpdateTaskStatusCommand(taskId, resource.status());
    }
}
