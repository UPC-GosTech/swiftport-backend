package com.gostech.swiftportbackend.execution.application.internal.commandservices;

import com.gostech.swiftportbackend.execution.domain.exceptions.ExecutionNoFoundException;
import com.gostech.swiftportbackend.execution.domain.exceptions.ExecutionNotSavedException;
import com.gostech.swiftportbackend.execution.domain.exceptions.ExecutionNotUpdatedException;
import com.gostech.swiftportbackend.execution.domain.model.aggregates.Execution;
import com.gostech.swiftportbackend.execution.domain.model.commands.*;
import com.gostech.swiftportbackend.execution.domain.model.entities.ExecutionEmployee;
import com.gostech.swiftportbackend.execution.domain.model.entities.ExecutionEquipment;
import com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories.ExecutionEmployeeRepository;
import com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories.ExecutionEquipmentRepository;
import com.gostech.swiftportbackend.plannification.domain.model.entities.TaskProgramming;
import com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories.TaskProgrammingRepository;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Equipment;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.EquipmentRepository;
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
    private final ExecutionEmployeeRepository executionEmployeeRepository;
    private final ExecutionEquipmentRepository executionEquipmentRepository;
    private final EquipmentRepository equipmentRepository;
    private final EmployeeRepository employeeRepository;

    public ExecutionCommandServiceImpl(
            ExecutionRepository executionRepository,
            TaskProgrammingRepository taskProgrammingRepository,
            ExecutionEquipmentRepository executionEquipmentRepository,
            ExecutionEmployeeRepository executionEmployeeRepository,
            EquipmentRepository equipmentRepository,
            EmployeeRepository employeeRepository
    ) {
        this.executionRepository = executionRepository;
        this.taskProgrammingRepository = taskProgrammingRepository;
        this.executionEquipmentRepository = executionEquipmentRepository;
        this.executionEmployeeRepository = executionEmployeeRepository;
        this.equipmentRepository = equipmentRepository;
        this.employeeRepository = employeeRepository;
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
            throw new ExecutionNotSavedException(ex.getMessage());
        }
        return execution.getId();
    }

    @Override
    public Long handle(AddEmployeeIdToExecutionCommand command) {
        Execution execution = executionRepository.findById(command.executionId())
                .orElseThrow(() -> new ExecutionNoFoundException(command.executionId()));
        Employee employee = employeeRepository.findById(command.employeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        var executionEmployee = new ExecutionEmployee(execution, employee);
        try {
            executionEmployeeRepository.save(executionEmployee);
        } catch (Exception ex) {
            throw new ExecutionNotSavedException(ex.getMessage());
        }
        return executionEmployee.getId();
    }

    @Override
    public Long handle(AddEquipmentIdToExecutionCommand command) {
        Execution execution = executionRepository.findById(command.executionId())
                .orElseThrow(() -> new ExecutionNoFoundException(command.executionId()));

        Equipment equipment = equipmentRepository.findById(command.equipmentId())
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found"));

        var executionEquipment = new ExecutionEquipment(execution, equipment);
        try {
            executionEquipmentRepository.save(executionEquipment);
        } catch (Exception ex) {
            throw new ExecutionNotSavedException(ex.getMessage());
        }
        return executionEquipment.getId();
    }

    @Override
    public Optional<Execution> handle(UpdateExecutionCommand command) {
        Execution execution = executionRepository.findById(command.executionId())
                .orElseThrow(() -> new ExecutionNoFoundException(command.executionId()));
        execution.addModifications(command);
        try {
            executionRepository.save(execution);
        } catch (Exception ex) {
            throw new ExecutionNotUpdatedException(ex.getMessage());
        }
        return Optional.of(execution);
    }

    @Override
    public Optional<Execution> handle(UpdateTaskExecutionStatusCommand command) {
        Execution execution = executionRepository.findById(command.executionId())
                .orElseThrow(() -> new ExecutionNoFoundException(command.executionId()));
        execution.updateTaskExecutionStatus(command.taskExecutionStatus());
        try {
            executionRepository.save(execution);
        } catch (Exception ex) {
            throw new ExecutionNotUpdatedException(ex.getMessage());
        }
        return Optional.of(execution);
    }
}
