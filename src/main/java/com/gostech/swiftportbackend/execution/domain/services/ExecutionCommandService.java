package com.gostech.swiftportbackend.execution.domain.services;

import com.gostech.swiftportbackend.execution.domain.model.aggregates.Execution;
import com.gostech.swiftportbackend.execution.domain.model.commands.*;

import java.util.Optional;

public interface ExecutionCommandService {
    Long handle(CreateExecutionCommand command);
    Long handle(AddEmployeeIdToExecutionCommand command);
    Long handle(AddEquipmentIdToExecutionCommand command);
    Optional<Execution> handle(UpdateExecutionCommand command);
    Optional<Execution> handle(UpdateTaskExecutionStatusCommand command);
}
