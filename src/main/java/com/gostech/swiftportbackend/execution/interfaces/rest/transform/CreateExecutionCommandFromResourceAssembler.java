package com.gostech.swiftportbackend.execution.interfaces.rest.transform;

import com.gostech.swiftportbackend.execution.domain.model.commands.CreateExecutionCommand;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.CreateExecutionResource;

public class CreateExecutionCommandFromResourceAssembler {
    public static CreateExecutionCommand toCommandFromResource(CreateExecutionResource resource) {
        return new CreateExecutionCommand(
                resource.taskProgrammingId(),
                resource.taskExecutionStatus(),
                resource.start(),
                resource.end(),
                resource.tenantId()
        );
    }
}
