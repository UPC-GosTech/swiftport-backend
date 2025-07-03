package com.gostech.swiftportbackend.execution.application.internal.commandservices;

import com.gostech.swiftportbackend.execution.domain.model.aggregates.Execution;
import com.gostech.swiftportbackend.execution.domain.model.commands.*;
import com.gostech.swiftportbackend.plannification.domain.model.entities.TaskProgramming;
import com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories.TaskProgrammingRepository;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.EmployeeId;
import com.gostech.swiftportbackend.execution.domain.model.valueobjects.EquipmentId;
import com.gostech.swiftportbackend.execution.domain.model.valueobjects.TaskProgrammingId;
import com.gostech.swiftportbackend.execution.domain.services.ExecutionCommandService;
import com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories.ExecutionRepository;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecutionCommandServiceImpl implements ExecutionCommandService {
    private final ExecutionRepository executionRepository;
    private final TaskProgrammingRepository taskProgrammingRepository;

    public ExecutionCommandServiceImpl(ExecutionRepository executionRepository, TaskProgrammingRepository taskProgrammingRepository) {
        this.executionRepository = executionRepository;
        this.taskProgrammingRepository = taskProgrammingRepository;
    }

    @Override
    public Long handle(CreateExecutionCommand command) {
        if (executionRepository.existsByTaskProgrammingId(command.taskProgrammingId()))
            throw new IllegalArgumentException("Task Programming %s already exists".formatted(command.taskProgrammingId()));

        Long tenantId = TenantContext.getCurrentTenantId();
        if (tenantId == null) {
            throw new RuntimeException("Tenant context not found");
        }

        TaskProgramming taskProgramming = taskProgrammingRepository.findById(command.taskProgrammingId())
                .orElseThrow(() -> new IllegalArgumentException("Task Programming not found"));

        var execution = new Execution(tenantId, command);
        try {
            execution.setTaskProgramming(taskProgramming);
            executionRepository.save(execution);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error saving execution %s".formatted(ex.getMessage()));
        }
        return execution.getId();
    }

    @Override
    public Long handle(AddEmployeeIdToExecutionCommand command) {
        throw new UnsupportedOperationException("Asignación de empleados a ejecución no implementada. Usar entidad intermedia.");
    }

    @Override
    public Long handle(AddEquipmentIdToExecutionCommand command) {
        throw new UnsupportedOperationException("Asignación de equipos a ejecución no implementada. Usar entidad intermedia.");
    }

    @Override
    public Optional<Execution> handle(UpdateExecutionCommand command) {
        Execution execution = executionRepository.findById(command.executionId())
                .orElseThrow(() -> new IllegalArgumentException("Execution not found"));
        execution.addModifications(command);
        try {
            executionRepository.save(execution);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error updating execution %s".formatted(ex.getMessage()));
        }
        return Optional.of(execution);
    }

    @Override
    public Optional<Execution> handle(UpdateTaskExecutionStatusCommand command) {
        Execution execution = executionRepository.findById(command.executionId())
                .orElseThrow(() -> new IllegalArgumentException("Execution not found"));
        execution.updateTaskExecutionStatus(command.taskExecutionStatus());
        try {
            executionRepository.save(execution);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error updating execution %s".formatted(ex.getMessage()));
        }
        return Optional.of(execution);
    }
}
