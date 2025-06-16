package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEmployeeCommand;

public interface EmployeeCommandService {
    Long handle(CreateEmployeeCommand command);
}
