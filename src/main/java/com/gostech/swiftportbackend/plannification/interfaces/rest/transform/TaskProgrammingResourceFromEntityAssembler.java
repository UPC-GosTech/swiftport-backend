package com.gostech.swiftportbackend.plannification.interfaces.rest.transform;

import com.gostech.swiftportbackend.plannification.domain.model.entities.TaskProgramming;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.TaskProgrammingResource;

public class TaskProgrammingResourceFromEntityAssembler {
    public static TaskProgrammingResource toResourceFromEntity(TaskProgramming entity) {
        return new TaskProgrammingResource(
                entity.getId(),
                entity.getResourceReference().resourceType(),
                entity.getResourceReference().resourceId(),
                entity.getTimeInterval().start(),
                entity.getTimeInterval().end(),
                entity.getProgrammingStatus()
        );
    }
}
