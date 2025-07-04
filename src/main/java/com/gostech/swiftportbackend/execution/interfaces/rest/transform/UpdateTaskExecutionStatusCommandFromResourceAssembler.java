package com.gostech.swiftportbackend.execution.interfaces.rest.transform;

import com.gostech.swiftportbackend.execution.domain.model.commands.UpdateTaskExecutionStatusCommand;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.UpdateTaskExecutionStatusResource;

public class UpdateTaskExecutionStatusCommandFromResourceAssembler {
    public static UpdateTaskExecutionStatusCommand toCommandFromResource(Long executionId, UpdateTaskExecutionStatusResource resource) {
        return new UpdateTaskExecutionStatusCommand(
                executionId,
                resource.taskExecutionStatus()
        );
    }
}
