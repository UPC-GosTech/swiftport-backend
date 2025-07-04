package com.gostech.swiftportbackend.execution.interfaces.rest.transform;

import com.gostech.swiftportbackend.execution.domain.model.commands.UpdateExecutionCommand;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.UpdateExecutionResource;

import java.time.LocalDateTime;

public class UpdateExecutionCommandFromResourceAssembler {
    public static UpdateExecutionCommand toCommandFromResource(Long executionId, UpdateExecutionResource resource) {
        return new UpdateExecutionCommand(
                executionId,
                resource.reason(),
                resource.taskProgrammingId(),
                resource.taskExecutionStatus(),
                resource.start(),
                resource.end()
        );
    }
}
