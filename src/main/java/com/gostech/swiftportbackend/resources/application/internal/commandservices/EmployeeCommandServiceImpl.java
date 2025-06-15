package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEmployeeCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.FullName;
import com.gostech.swiftportbackend.resources.domain.services.EmployeeCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCommandServiceImpl implements EmployeeCommandService {
    private final EmployeeRepository employeeRepository;

    public EmployeeCommandServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Long handle(CreateEmployeeCommand command) {
        if (employeeRepository.existsByName(new FullName(command.name(), command.lastName())))
            throw new IllegalArgumentException("Employee with name %s already exists".formatted(new FullName(command.name(), command.lastName())));
        var employee = new Employee(command);
        try {
            employeeRepository.save(employee);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving employee: %s".formatted(e.getMessage()));
        }
        return employee.getId();
    }
}
