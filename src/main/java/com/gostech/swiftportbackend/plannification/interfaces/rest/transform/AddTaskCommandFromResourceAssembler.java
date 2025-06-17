package com.gostech.swiftportbackend.plannification.interfaces.rest.transform;

import com.gostech.swiftportbackend.plannification.domain.model.commands.AddTaskCommand;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.CreateTaskResource;

public class AddTaskCommandFromResourceAssembler {
    public static AddTaskCommand toCommandFromResource(CreateTaskResource resource) {
        return new AddTaskCommand(
                resource.activityId(),
                resource.title(),
                resource.description(),
                resource.status()
        );
    }
}
