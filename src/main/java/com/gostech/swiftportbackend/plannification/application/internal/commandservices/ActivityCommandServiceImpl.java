package com.gostech.swiftportbackend.plannification.application.internal.commandservices;

import com.gostech.swiftportbackend.plannification.application.internal.outboundservice.acl.ExternalReservationService;
import com.gostech.swiftportbackend.plannification.domain.exceptions.*;
import com.gostech.swiftportbackend.plannification.domain.model.aggregates.Activity;
import com.gostech.swiftportbackend.plannification.domain.model.commands.*;
import com.gostech.swiftportbackend.plannification.domain.model.entities.Task;
import com.gostech.swiftportbackend.plannification.domain.model.entities.TaskProgramming;
import com.gostech.swiftportbackend.plannification.domain.model.valueobjects.ActicityCode;
import com.gostech.swiftportbackend.plannification.domain.services.ActivityCommandService;
import com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories.ActivityRepository;
import com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories.TaskProgrammingRepository;
import com.gostech.swiftportbackend.plannification.infrastructure.persistence.jpa.repositories.TaskRepository;
import com.gostech.swiftportbackend.resources.domain.exceptions.EmployeeNotFoundException;
import com.gostech.swiftportbackend.resources.domain.exceptions.EmployeeNotSavedException;
import com.gostech.swiftportbackend.resources.domain.exceptions.LocationNotFoundException;
import com.gostech.swiftportbackend.resources.domain.exceptions.ZoneNotFoundException;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import com.gostech.swiftportbackend.resources.domain.model.entities.Location;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.LocationRepository;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.ZoneRepository;
import com.gostech.swiftportbackend.shared.domain.exceptions.TenantNotFoundException;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityCommandServiceImpl implements ActivityCommandService {
    private final ActivityRepository activityRepository;
    private final TaskRepository taskRepository;
    private final TaskProgrammingRepository taskProgrammingRepository;
    private final ZoneRepository zoneRepository;
    private final LocationRepository locationRepository;
    private final ExternalReservationService externalReservationService;

    public ActivityCommandServiceImpl(ActivityRepository activityRepository, TaskRepository taskRepository, TaskProgrammingRepository taskProgrammingRepository, ZoneRepository zoneRepository, LocationRepository locationRepository, ExternalReservationService externalReservationService) {
        this.activityRepository = activityRepository;
        this.taskRepository = taskRepository;
        this.taskProgrammingRepository = taskProgrammingRepository;
        this.zoneRepository = zoneRepository;
        this.locationRepository = locationRepository;
        this.externalReservationService = externalReservationService;
    }

    @Override
    public Long handle(CreateActivityCommand command) {
        if (activityRepository.existsByActivityCode(new ActicityCode(command.activityCode())))
            throw new ActivityCodeAlreadyExistsException(command.activityCode());

        Long tenantId = TenantContext.getCurrentTenantId();
        if (tenantId == null) {
            throw new TenantNotFoundException();
        }

        Location locationOrigin = locationRepository.findById(command.locationOrigin())
                .orElseThrow(() -> new LocationNotFoundException(command.locationOrigin()));

        Location locationDestination = locationRepository.findById(command.locationDestination())
                .orElseThrow(() -> new LocationNotFoundException(command.locationOrigin()));

        Zone zoneOrigin = zoneRepository.findById(command.zoneOrigin())
                .orElseThrow(() -> new ZoneNotFoundException(command.zoneOrigin()));

        Zone zoneDestination = zoneRepository.findById(command.zoneDestination())
                .orElseThrow(() -> new ZoneNotFoundException(command.zoneOrigin()));

        var activity = new Activity(tenantId, command);

        try {
            activity.setLocationOrigin(locationOrigin);
            activity.setLocationDestination(locationDestination);
            activity.setZoneOrigin(zoneOrigin);
            activity.setZoneDestination(zoneDestination);
            activityRepository.save(activity);
        } catch (Exception e) {
            throw new ActivityNotSavedException(e.getMessage());
        }
        return activity.getId();
    }

    @Override
    public Long handle(AddTaskCommand command) {
        if (taskRepository.existsByTitle(command.title()))
            throw new TaskTitleAlreadyExistsException();
        Activity activity = activityRepository.findById(command.activityId())
            .orElseThrow(() -> new ActivityNotFoundException(command.activityId()));
        var task = new Task(command);
        try {
            task.setActivity(activity);
            activity.addTask(task);
            taskRepository.save(task);
        } catch (Exception e) {
            throw new TaskNotSavedException(e.getMessage());
        }
        return task.getId();
    }

    @Override
    public Long handle(AddTaskProgrammingCommand command) {
        Task task = taskRepository.findById(command.taskId())
            .orElseThrow(() -> new TaskNotFoundException(command.taskId()));

        var reserveId = externalReservationService.fetchReservationByResourceReference(command.resourceId(), command.resourceType());

        if (!reserveId.isEmpty()) {
            if (externalReservationService.overlaps(reserveId.get().reservationId(), command.start(), command.end()))
                throw new ReservationDateOverlappedException();
        }

        reserveId = externalReservationService.createReservation(
                command.resourceType(),
                command.resourceId(),
                command.start(),
                command.end()
        );

        var taskProgramming = new TaskProgramming(reserveId.get().reservationId(), command);
        try {
            taskProgramming.setTask(task);
            task.addProgramming(taskProgramming);
            taskProgrammingRepository.save(taskProgramming);
        }  catch (Exception e) {
            throw new TaskNotSavedException(e.getMessage());
        }
        return taskProgramming.getId();
    }

    @Override
    public Optional<Task> handle(UpdateEmployeeAssignedOnTaskCommand command) {
        Task task = taskRepository.findById(command.taskId())
            .orElseThrow(() -> new TaskNotFoundException(command.taskId()));
        try {
            task.updateEmployeeAssigned(command.employeeId());
            taskRepository.save(task);
        }  catch (Exception e) {
            throw new EmployeeNotSavedException(e.getMessage());
        }
        return Optional.of(task);
    }

    @Override
    public Optional<Task> handle(UpdateTaskDescriptionCommand  command) {
        Task task = taskRepository.findById(command.taskId())
            .orElseThrow(() -> new TaskNotFoundException(command.taskId()));
        try {
            task.updateTaskDescription(command.description());
            taskRepository.save(task);
        } catch (Exception e) {
            throw new TaskNotUpdatedException(e.getMessage());
        }
        return Optional.of(task);
    }

    @Override
    public Optional<Task> handle(UpdateTaskStatusCommand command) {
        Task task = taskRepository.findById(command.taskId())
            .orElseThrow(() -> new TaskNotFoundException(command.taskId()));
        try {
            task.updateTaskStatus(command.status());
            taskRepository.save(task);
        } catch (Exception e) {
            throw new TaskNotUpdatedException(e.getMessage());
        }
        return Optional.of(task);
    }

    @Override
    public Optional<TaskProgramming> handle(UpdateTaskProgrammingStatusCommand command) {
        TaskProgramming taskProgramming = taskProgrammingRepository.findById(command.taskProgrammingId())
            .orElseThrow(() -> new TaskProgrammingNotFoundException(command.taskProgrammingId()));
        try {
            taskProgramming.updateStatus(command.programmingStatus());
            taskProgrammingRepository.save(taskProgramming);
        } catch (Exception e) {
            throw new TaskProgrammingNotUpdatedException(e.getMessage());
        }
        return Optional.of(taskProgramming);
    }

    @Override
    public Optional<TaskProgramming> handle(UpdateTaskProgrammingTimeIntervalCommand command) {
        TaskProgramming taskProgramming = taskProgrammingRepository.findById(command.taskProgrammingId())
            .orElseThrow(() -> new TaskProgrammingNotFoundException(command.taskProgrammingId()));

        /*
        * var reserveId = externalReservationService.fetchReservationByResourceReference(command.resourceId(), command.resourceType());

        if (!reserveId.isEmpty()) {
            if (externalReservationService.overlaps(reserveId.get().reservationId(), command.start(), command.end()))
                throw new IllegalArgumentException("Reservation date overlaps");
        }

        reserveId = externalReservationService.createReservation(
                command.resourceType(),
                command.resourceId(),
                command.start(),
                command.end()
        );

        var taskProgramming = new TaskProgramming(reserveId.get().reservationId(), command);
        * */
        var success = externalReservationService.updateReservationTimeInterval(taskProgramming.getReservationId().reservationId(), command.start(), command.end());
        if (success) throw new ReservationDateOverlappedException();

        try {
            taskProgrammingRepository.save(taskProgramming);
        } catch (Exception e) {
            throw new TaskProgrammingNotUpdatedException(e.getMessage());
        }
        return Optional.of(taskProgramming);
    }

    @Override
    public Optional<Activity> handle(UpdateActivityStatusCommand command) {
        Activity activity = activityRepository.findById(command.activityId())
            .orElseThrow(() -> new ActivityNotFoundException(command.activityId()));
        try {
            activity.updateActivityStatus(command.activityStatus());
            activityRepository.save(activity);
        } catch (Exception e) {
            throw new TaskProgrammingNotUpdatedException(e.getMessage());
        }
        return Optional.of(activity);
    }
}
