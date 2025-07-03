package com.gostech.swiftportbackend.plannification.domain.services;

import com.gostech.swiftportbackend.plannification.domain.model.aggregates.Activity;
import com.gostech.swiftportbackend.plannification.domain.model.entities.Task;
import com.gostech.swiftportbackend.plannification.domain.model.entities.TaskProgramming;
import com.gostech.swiftportbackend.plannification.domain.model.queries.*;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.ResourceReference;

import java.util.List;
import java.util.Optional;

public interface ActivityQueryService {
    Optional<Activity> handle(GetActivityByIdQuery query);
    List<Activity> handle(GetAllActivitiesQuery query);
    Optional<Task> handle(GetTaskByIdQuery query);
    List<Task> handle(GetTasksByActivityIdQuery query);
    Optional<TaskProgramming> handle(GetTaskProgrammingByIdQuery query);
    List<TaskProgramming> handle(GetTaskProgrammingsByTaskIdQuery query);
    List<Task> handle(GetAllTasksQuery query);
    List<Task> handle(GetTasksByStatusQuery query);
    List<TaskProgramming> handle(GetAllTaskProgrammingsQuery query);
    List<Activity> handle(GetActivitiesByStatusQuery query);
    List<TaskProgramming> handle(GetTaskProgrammingsByActivityIdQuery query);
}
