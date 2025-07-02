package com.gostech.swiftportbackend.plannification.domain.services;

import com.gostech.swiftportbackend.plannification.domain.model.commands.AddTaskCommand;
import com.gostech.swiftportbackend.plannification.domain.model.commands.AddTaskProgrammingCommand;
import com.gostech.swiftportbackend.plannification.domain.model.commands.CreateActivityCommand;
import com.gostech.swiftportbackend.plannification.domain.model.commands.UpdateEmployeeAssignedOnTaskCommand;
import com.gostech.swiftportbackend.plannification.domain.model.entities.Task;

import java.util.Optional;

public interface ActivityCommandService {
    Long handle(CreateActivityCommand command);
    Long handle(AddTaskCommand command);
    Long handle(AddTaskProgrammingCommand command);
    Optional<Task> handle(UpdateEmployeeAssignedOnTaskCommand command);
}
