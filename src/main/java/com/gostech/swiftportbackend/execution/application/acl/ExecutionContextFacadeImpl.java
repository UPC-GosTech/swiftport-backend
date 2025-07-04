package com.gostech.swiftportbackend.execution.application.acl;

import com.gostech.swiftportbackend.execution.domain.model.commands.CreateExecutionCommand;
import com.gostech.swiftportbackend.execution.domain.services.ExecutionCommandService;
import com.gostech.swiftportbackend.execution.domain.services.ExecutionQueryService;
import com.gostech.swiftportbackend.execution.interfaces.acl.ExecutionContextFacade;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ExecutionContextFacadeImpl implements ExecutionContextFacade {
    private final ExecutionCommandService executionCommandService;
    private final ExecutionQueryService executionQueryService;

    public ExecutionContextFacadeImpl(ExecutionCommandService executionCommandService, ExecutionQueryService executionQueryService) {
        this.executionCommandService = executionCommandService;
        this.executionQueryService = executionQueryService;
    }

    public Long createExecution(
            Long taskProgrammingId,
            String taskExecutionStatus,
            LocalDateTime start,
            LocalDateTime end) {

        var createExecutionCommand = new CreateExecutionCommand(
                taskProgrammingId,
                taskExecutionStatus,
                start,
                end
        );
        return executionCommandService.handle(createExecutionCommand);
    }
}
