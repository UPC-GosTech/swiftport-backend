package com.gostech.swiftportbackend.execution.application.internal.commandservices;

import com.gostech.swiftportbackend.execution.domain.model.aggregates.Execution;
import com.gostech.swiftportbackend.execution.domain.model.commands.*;
import com.gostech.swiftportbackend.execution.domain.model.valueobjects.EmployeeId;
import com.gostech.swiftportbackend.execution.domain.model.valueobjects.EquipmentId;
import com.gostech.swiftportbackend.execution.domain.model.valueobjects.TaskProgrammingId;
import com.gostech.swiftportbackend.execution.domain.services.ExecutionCommandService;
import com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories.ExecutionRepository;
import org.springframework.stereotype.Service;

@Service
public class ExecutionCommandServiceImpl implements ExecutionCommandService {
    private final ExecutionRepository executionRepository;

    public ExecutionCommandServiceImpl(ExecutionRepository executionRepository) {
        this.executionRepository = executionRepository;
    }

    @Override
    public Long handle(CreateExecutionCommand command) {
        if (executionRepository.existsByTaskProgrammingId(new TaskProgrammingId(command.taskProgrammingId())))
            throw new IllegalArgumentException("Task Programming %s already exists".formatted(command.taskProgrammingId()));
        var execution = new Execution(command);
        try {
            executionRepository.save(execution);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error saving execution %s".formatted(ex.getMessage()));
        }
        return execution.getId();
    }

    @Override
    public Long handle(AddEmployeeIdToExecutionCommand command) {
        Execution execution = executionRepository.findById(command.executionId())
                .orElseThrow(() -> new IllegalArgumentException("Execution not found"));
        var employeeId = new EmployeeId(command.employeeId());
        try {
            execution.addEmployeeId(employeeId);
            executionRepository.save(execution);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error saving execution %s".formatted(ex.getMessage()));
        }
        return execution.getId();
    }

    @Override
    public Long handle(AddEquipmentIdToExecutionCommand command) {
        Execution execution = executionRepository.findById(command.executionId())
                .orElseThrow(() -> new IllegalArgumentException("Execution not found"));
        var equipmentId = new EquipmentId(command.equipmentId());
        try {
            execution.addEquipmentId(equipmentId);
            executionRepository.save(execution);
        }  catch (Exception ex) {
            throw new IllegalArgumentException("Error saving execution %s".formatted(ex.getMessage()));
        }
        return execution.getId();
    }

    @Override
    public Long handle(UpdateExecutionCommand command) {
        Execution execution = executionRepository.findById(command.executionId())
                .orElseThrow(() -> new IllegalArgumentException("Execution not found"));
        execution.addModifications(command);
        try {
            executionRepository.save(execution);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error updating execution %s".formatted(ex.getMessage()));
        }
        return execution.getId();
    }

    @Override
    public Long handle(UpdateTaskExecutionStatusCommand command) {
        Execution execution = executionRepository.findById(command.executionId())
                .orElseThrow(() -> new IllegalArgumentException("Execution not found"));
        execution.updateTaskExecutionStatus(command.taskExecutionStatus());
        try {
            executionRepository.save(execution);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error updating execution %s".formatted(ex.getMessage()));
        }
        return execution.getId();
    }
}
