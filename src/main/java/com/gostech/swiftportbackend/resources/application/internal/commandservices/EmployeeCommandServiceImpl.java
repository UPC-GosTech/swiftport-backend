package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.exceptions.EmployeeNameAlreadyExistsException;
import com.gostech.swiftportbackend.resources.domain.exceptions.EmployeeNotFoundException;
import com.gostech.swiftportbackend.resources.domain.exceptions.EmployeeNotSavedException;
import com.gostech.swiftportbackend.resources.domain.exceptions.PositionNotFoundException;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEmployeeCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.UpdateEmployeeStatusCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.FullName;
import com.gostech.swiftportbackend.resources.domain.services.EmployeeCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.PositionRepository;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Position;
import com.gostech.swiftportbackend.shared.domain.exceptions.TenantNotFoundException;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;
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
            throw new EmployeeNameAlreadyExistsException(command.name(), command.lastName());

        Long tenantId = TenantContext.getCurrentTenantId();
        if (tenantId == null) {
            throw new TenantNotFoundException();
        }

        Position position = positionRepository.findById(command.positionId())
            .orElseThrow(() -> new PositionNotFoundException(command.positionId()));

        var employee = new Employee(tenantId, command);
        try {
            employee.setPosition(position);
            employeeRepository.save(employee);
        } catch (Exception e) {
            throw new EmployeeNotSavedException(e.getMessage());
        }
        return employee.getId();
    }

    @Override
    public Optional<Employee> handle(UpdateEmployeeStatusCommand command) {
        Employee employee = employeeRepository.findById(command.employeeId())
                .orElseThrow(() -> new EmployeeNotFoundException(command.employeeId()));
        try {
            employee.updateStatus(command.status());
            employeeRepository.save(employee);
        } catch (Exception e) {
            throw new EmployeeNotSavedException(e.getMessage());
        }
        return Optional.of(employee);
    }
}
