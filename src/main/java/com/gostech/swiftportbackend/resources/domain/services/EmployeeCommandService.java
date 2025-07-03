package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEmployeeCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.UpdateEmployeeStatusCommand;

import java.util.Optional;

public interface EmployeeCommandService {
    Long handle(CreateEmployeeCommand command);
    Optional<Employee> handle(UpdateEmployeeStatusCommand command);
}
