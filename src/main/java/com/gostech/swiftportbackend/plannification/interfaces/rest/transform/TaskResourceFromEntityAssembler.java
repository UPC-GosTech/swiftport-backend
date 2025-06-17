package com.gostech.swiftportbackend.plannification.interfaces.rest.transform;

import com.gostech.swiftportbackend.plannification.domain.model.entities.Task;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.TaskResource;

public class TaskResourceFromEntityAssembler {
    public static TaskResource toResourceFromEntity(Task entity) {
        return new TaskResource(
                entity.getId(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getTitle()
        );
    }
}
