package com.gostech.swiftportbackend.execution.interfaces.rest.transform;

import com.gostech.swiftportbackend.execution.domain.model.aggregates.Execution;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.ExecutionResource;

public class ExecutionResourceFromEntityAssembler {
    public static ExecutionResource toResourceFromEntity(Execution entity) {
        return new ExecutionResource(
                entity.getId(),
                entity.getTaskProgrammingId().taskProgrammingId(),
                entity.getTaskExecutionStatus(),
                entity.getExecutionTimeFrame().start(),
                entity.getExecutionTimeFrame().end(),
                entity.getModificationReason(),
                entity.getTenantId().getValue()
        );
    }
}
