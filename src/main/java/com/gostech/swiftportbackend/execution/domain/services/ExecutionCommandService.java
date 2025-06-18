package com.gostech.swiftportbackend.execution.domain.services;

import com.gostech.swiftportbackend.execution.domain.model.commands.*;

public interface ExecutionCommandService {
    Long handle(CreateExecutionCommand command);
    Long handle(AddEmployeeIdToExecutionCommand command);
    Long handle(AddEquipmentIdToExecutionCommand command);
    Long handle(AddModificationReasonToExecutionCommand command);
    Long handle(UpdateTaskExecutionStatusCommand command);
}
