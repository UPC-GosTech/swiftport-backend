package com.gostech.swiftportbackend.plannification.application.internal.commandservices;

import com.gostech.swiftportbackend.plannification.domain.model.aggregates.Activity;
import com.gostech.swiftportbackend.plannification.domain.model.commands.*;
import com.gostech.swiftportbackend.plannification.domain.model.entities.Task;
import com.gostech.swiftportbackend.plannification.domain.model.entities.TaskProgramming;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ActicityCode;
import com.gostech.swiftportbackend.plannification.domain.services.ActivityCommandService;
import com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories.ActivityRepository;
import com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories.TaskProgrammingRepository;
import com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityCommandServiceImpl implements ActivityCommandService {
    private final ActivityRepository activityRepository;
    private final TaskRepository taskRepository;
    private final TaskProgrammingRepository taskProgrammingRepository;

    public ActivityCommandServiceImpl(ActivityRepository activityRepository, TaskRepository taskRepository, TaskProgrammingRepository taskProgrammingRepository) {
        this.activityRepository = activityRepository;
        this.taskRepository = taskRepository;
        this.taskProgrammingRepository = taskProgrammingRepository;
    }

    @Override
    public Long handle(CreateActivityCommand command) {
        if (activityRepository.existsByActivityCode(new ActicityCode(command.activityCode())))
            throw new IllegalArgumentException("Activity with code %s already exists".formatted(command.activityCode()));
        var activity = new Activity(command);
        try {
            activityRepository.save(activity);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving activity: %s".formatted(e.getMessage()));
        }
        return activity.getId();
    }

    @Override
    public Long handle(AddTaskCommand command) {
        if (taskRepository.existsByTitle(command.title()))
            throw new IllegalArgumentException("Task with title %s already exists".formatted(command.title()));
        Activity activity = activityRepository.findById(command.activityId())
            .orElseThrow(() -> new IllegalArgumentException("Activity not found"));
        var task = new Task(command);
        try {
            task.setActivity(activity);
            activity.addTask(task);
            taskRepository.save(task);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving task: %s".formatted(e.getMessage()));
        }
        return task.getId();
    }

    @Override
    public Long handle(AddTaskProgrammingCommand command) {
        Task task = taskRepository.findById(command.taskId())
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        var taskProgramming = new TaskProgramming(command);
        try {
            taskProgramming.setTask(task);
            task.addProgramming(taskProgramming);
            taskProgrammingRepository.save(taskProgramming);
        }  catch (Exception e) {
            throw new IllegalArgumentException("Error saving task programming: %s".formatted(e.getMessage()));
        }
        return taskProgramming.getId();
    }

    @Override
    public Optional<Task> handle(UpdateEmployeeAssignedOnTaskCommand command) {
        Task task = taskRepository.findById(command.taskId())
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        try {
            task.updateEmployeeAssigned(command.employeeId());
            taskRepository.save(task);
        }  catch (Exception e) {
            throw new IllegalArgumentException("Error updating employee Id on task: %s".formatted(e.getMessage()));
        }
        return Optional.of(task);
    }

    @Override
    public Optional<Task> handle(UpdateTaskDescriptionCommand  command) {
        Task task = taskRepository.findById(command.taskId())
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        try {
            task.updateTaskDescription(command.description());
            taskRepository.save(task);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating task description: %s".formatted(e.getMessage()));
        }
        return Optional.of(task);
    }

    @Override
    public Optional<Task> handle(UpdateTaskStatusCommand command) {
        Task task = taskRepository.findById(command.taskId())
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        try {
            task.updateTaskStatus(command.status());
            taskRepository.save(task);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating task status: %s".formatted(e.getMessage()));
        }
        return Optional.of(task);
    }

    @Override
    public Optional<TaskProgramming> handle(UpdateTaskProgrammingStatusCommand command) {
        TaskProgramming taskProgramming = taskProgrammingRepository.findById(command.taskProgrammingId())
            .orElseThrow(() -> new IllegalArgumentException("TaskProgramming not found"));
        try {
            taskProgramming.updateStatus(command.programmingStatus());
            taskProgrammingRepository.save(taskProgramming);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating task programming: %s".formatted(e.getMessage()));
        }
        return Optional.of(taskProgramming);
    }

    @Override
    public Optional<TaskProgramming> handle(UpdateTaskProgrammingTimeIntervalCommand command) {
        TaskProgramming taskProgramming = taskProgrammingRepository.findById(command.taskProgrammingId())
            .orElseThrow(() -> new IllegalArgumentException("TaskProgramming not found"));
        try {
            taskProgramming.updateTime(command.start(), command.end());
            taskProgrammingRepository.save(taskProgramming);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating task programming: %s".formatted(e.getMessage()));
        }
        return Optional.of(taskProgramming);
    }

    @Override
    public Optional<Activity> handle(UpdateActivityStatusCommand command) {
        Activity activity = activityRepository.findById(command.activityId())
            .orElseThrow(() -> new IllegalArgumentException("Activity not found"));
        try {
            activity.updateActivityStatus(command.activityStatus());
            activityRepository.save(activity);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating activity status: %s".formatted(e.getMessage()));
        }
        return Optional.of(activity);
    }
}
