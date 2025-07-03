package com.gostech.swiftportbackend.plannification.interfaces.rest.controllers;

import com.gostech.swiftportbackend.plannification.domain.model.queries.GetAllTasksQuery;
import com.gostech.swiftportbackend.plannification.domain.model.queries.GetTaskByIdQuery;
import com.gostech.swiftportbackend.plannification.domain.model.queries.GetTasksByActivityIdQuery;
import com.gostech.swiftportbackend.plannification.domain.model.queries.GetTasksByStatusQuery;
import com.gostech.swiftportbackend.plannification.domain.services.ActivityCommandService;
import com.gostech.swiftportbackend.plannification.domain.services.ActivityQueryService;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.*;
import com.gostech.swiftportbackend.plannification.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/tasks", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Tasks", description = "Available Task Endpoints")
public class TaskController {
    private final ActivityCommandService activityCommandService;
    private final ActivityQueryService activityQueryService;

    public TaskController(ActivityCommandService activityCommandService, ActivityQueryService activityQueryService) {
        this.activityCommandService = activityCommandService;
        this.activityQueryService = activityQueryService;
    }

    @PostMapping
    @Operation(summary = "Create new task", description = "Create new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    public ResponseEntity<TaskResource> createTask(@RequestBody CreateTaskResource resource) {
        var addTaskCommand = AddTaskCommandFromResourceAssembler.toCommandFromResource(resource);
        var taskId = activityCommandService.handle(addTaskCommand);
        if (taskId == null || taskId == 0L) return ResponseEntity.badRequest().build();
        var getTaskByIdQuery = new GetTaskByIdQuery(taskId);
        var task = activityQueryService.handle(getTaskByIdQuery);
        if (task.isEmpty()) return ResponseEntity.badRequest().build();
        var taskEntity = task.get();
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(taskEntity);
        return new ResponseEntity<>(taskResource, HttpStatus.CREATED);
    }

    @GetMapping("/{taskId}")
    @Operation(summary = "Get task by id", description = "Get task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found"),
            @ApiResponse(responseCode = "404", description = "Task not found")})
    public ResponseEntity<TaskResource> getTaskById(@PathVariable Long taskId) {
        var getTaskByIdQuery = new GetTaskByIdQuery(taskId);
        var task = activityQueryService.handle(getTaskByIdQuery);
        if (task.isEmpty()) return ResponseEntity.notFound().build();
        var taskEntity = task.get();
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(taskEntity);
        return ResponseEntity.ok(taskResource);
    }

    @GetMapping("/activities/{activityId}")
    @Operation(summary = "Get tasks by activity id", description = "Get all tasks for a given activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks found"),
            @ApiResponse(responseCode = "404", description = "Activity or tasks not found")})
    public ResponseEntity<List<TaskResource>> getTasksByActivityId(@PathVariable Long activityId) {
        var taskList = activityQueryService.handle(new GetTasksByActivityIdQuery(activityId));
        if (taskList.isEmpty()) return ResponseEntity.notFound().build();
        var resources = taskList.stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PatchMapping("/{taskId}/employeeId")
    @Operation(summary = "Update employee id assgined on task", description = "Updates only the employee Id assigned to a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task Employee Id updated"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResource> updateTaskEmployeeId(@PathVariable Long taskId, @RequestBody UpdateEmployeeIdResource resource) {
        var updateEmployeeId = UpdateTaskEmployeeIdCommandFromResourceAssembler.toCommandFromResource(taskId, resource);
        var updatedTask = activityCommandService.handle(updateEmployeeId);
        if (updatedTask.isEmpty()) return ResponseEntity.notFound().build();
        var updatedTaskEntity = updatedTask.get();
        var updatedTaskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(updatedTaskEntity);
        return ResponseEntity.ok(updatedTaskResource);
    }

    @PatchMapping("/{taskId}/description")
    @Operation(summary = "Update task description", description = "Updates only the task description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task description updated"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResource> updateTaskDescription(@PathVariable Long taskId, @RequestBody UpdateTaskDescriptionResource resource) {
        var command = UpdateTaskDescriptionCommandFromResourceAssembler.toCommandFromResource(taskId, resource);
        var updatedTask = activityCommandService.handle(command);
        if (updatedTask.isEmpty()) return ResponseEntity.notFound().build();
        var updatedTaskEntity = updatedTask.get();
        var updatedTaskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(updatedTaskEntity);
        return ResponseEntity.ok(updatedTaskResource);
    }

    @PatchMapping("/{taskId}/status")
    @Operation(summary = "Update task status", description = "Updates only the status of a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task status updated"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResource> updateTaskStatus(@PathVariable Long taskId, @RequestBody UpdateTaskStatusResource resource) {
        var command = UpdateTaskStatusCommandFromResourceAssembler.toCommandFromResource(taskId, resource);
        var updatedTask = activityCommandService.handle(command);
        if (updatedTask.isEmpty()) return ResponseEntity.notFound().build();
        var updatedTaskEntity = updatedTask.get();
        var updatedTaskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(updatedTaskEntity);
        return ResponseEntity.ok(updatedTaskResource);
    }

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Get all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks found"),
            @ApiResponse(responseCode = "404", description = "Tasks not found")
    })
    public ResponseEntity<List<TaskResource>> getAllTasks() {
        var taskList = activityQueryService.handle(new GetAllTasksQuery());
        if (taskList.isEmpty()) return ResponseEntity.badRequest().build();
        var taskResources = taskList.stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(taskResources);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get tasks by status", description = "Get all tasks that match a given status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks found"),
            @ApiResponse(responseCode = "404", description = "No tasks found")
    })
    public ResponseEntity<List<TaskResource>> getTasksByStatus(@PathVariable String status) {
        var taskList = activityQueryService.handle(new GetTasksByStatusQuery(status));
        if (taskList.isEmpty()) return ResponseEntity.notFound().build();
        var taskResources = taskList.stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(taskResources);
    }

}
