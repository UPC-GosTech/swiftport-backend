package com.gostech.swiftportbackend.plannification.interfaces.rest.transform;

import com.gostech.swiftportbackend.plannification.domain.model.commands.UpdateTaskDescriptionCommand;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.UpdateTaskDescriptionResource;

public class UpdateTaskDescriptionCommandFromResourceAssembler {
    public static UpdateTaskDescriptionCommand toCommandFromResource(Long taskId, UpdateTaskDescriptionResource resource) {
        return new UpdateTaskDescriptionCommand(taskId, resource.description());
    }
}
