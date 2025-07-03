package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEmployeeCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.UpdateEmployeeStatusCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.FullName;
import com.gostech.swiftportbackend.resources.domain.services.EmployeeCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.PositionRepository;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Position;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeCommandServiceImpl implements EmployeeCommandService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    public EmployeeCommandServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public Long handle(CreateEmployeeCommand command) {
        if (employeeRepository.existsByName(new FullName(command.name(), command.lastName())))
            throw new IllegalArgumentException("Employee with name %s already exists".formatted(new FullName(command.name(), command.lastName())));
        Position position = positionRepository.findById(command.positionId())
            .orElseThrow(() -> new IllegalArgumentException("Position not found"));
        var employee = new Employee(command, position);
        try {
            employeeRepository.save(employee);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving employee: %s".formatted(e.getMessage()));
        }
        return employee.getId();
    }

    @Override
    public Optional<Employee> handle(UpdateEmployeeStatusCommand command) {
        Employee employee = employeeRepository.findById(command.employeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee with id %s does not exist".formatted(command.employeeId())));
        try {
            employee.updateStatus(command.status());
            employeeRepository.save(employee);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving employee: %s".formatted(e.getMessage()));
        }
        return Optional.of(employee);
    }
}
