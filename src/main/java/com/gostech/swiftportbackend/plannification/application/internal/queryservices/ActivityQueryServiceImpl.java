package com.gostech.swiftportbackend.plannification.application.internal.queryservices;

import com.gostech.swiftportbackend.plannification.domain.model.aggregates.Activity;
import com.gostech.swiftportbackend.plannification.domain.model.entities.Task;
import com.gostech.swiftportbackend.plannification.domain.model.entities.TaskProgramming;
import com.gostech.swiftportbackend.plannification.domain.model.queries.*;
import com.gostech.swiftportbackend.plannification.domain.services.ActivityQueryService;
import com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories.ActivityRepository;
import com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories.TaskProgrammingRepository;
import com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityQueryServiceImpl implements ActivityQueryService {
    private final ActivityRepository activityRepository;
    private final TaskRepository taskRepository;
    private final TaskProgrammingRepository taskProgrammingRepository;

    public ActivityQueryServiceImpl(ActivityRepository activityRepository, TaskRepository taskRepository, TaskProgrammingRepository taskProgrammingRepository) {
        this.activityRepository = activityRepository;
        this.taskRepository = taskRepository;
        this.taskProgrammingRepository = taskProgrammingRepository;
    }

    @Override
    public Optional<Activity> handle(GetActivityByIdQuery query) {
        return activityRepository.findById(query.id());
    }

    @Override
    public List<Activity> handle(GetAllActivitiesQuery query) {
        return activityRepository.findAll();
    }

    @Override
    public Optional<Task> handle(GetTaskByIdQuery query) {
        return taskRepository.findById(query.id());
    }

    @Override
    public List<Task> handle(GetTasksByActivityIdQuery query) {
        return taskRepository.findByActivityId(query.activityId());
    }

    @Override
    public List<TaskProgramming> handle(GetTaskProgrammingsByTaskIdQuery query) {
        return taskProgrammingRepository.findByTaskId(query.taskId());
    }
}
