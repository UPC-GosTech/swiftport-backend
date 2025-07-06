package com.gostech.swiftportbackend.plannification.interfaces.rest.transform;

import com.gostech.swiftportbackend.plannification.domain.model.commands.AddTaskProgrammingCommand;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.CreateTaskProgrammingResource;

public class AddTaskProgrammingCommandFromResourceAssembler {
    public static AddTaskProgrammingCommand toCommandFromResource(CreateTaskProgrammingResource resource) {
        return new AddTaskProgrammingCommand(
                resource.taskId(),
                resource.resourceType(),
                resource.resourceId(),
                resource.programmingStatus(),
                resource.start(),
                resource.end()
        );
    }
}
