package com.gostech.swiftportbackend.plannification.interfaces.rest.controllers;

import com.gostech.swiftportbackend.plannification.domain.model.queries.GetTaskByIdQuery;
import com.gostech.swiftportbackend.plannification.domain.model.queries.GetTasksByActivityIdQuery;
import com.gostech.swiftportbackend.plannification.domain.services.ActivityCommandService;
import com.gostech.swiftportbackend.plannification.domain.services.ActivityQueryService;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.CreateTaskResource;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.TaskResource;
import com.gostech.swiftportbackend.plannification.interfaces.rest.transform.AddTaskCommandFromResourceAssembler;
import com.gostech.swiftportbackend.plannification.interfaces.rest.transform.TaskResourceFromEntityAssembler;
import com.gostech.swiftportbackend.plannification.interfaces.rest.transform.UpdateTaskEmployeeIdCommandFromResourceAssembler;
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

    @PatchMapping("/{taskId}/employeeId/{employeeId}")
    @Operation(summary = "Update employee id assgined on task", description = "Updates only the employee Id assigned to a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task Employee Id updated"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResource> updateTaskEmployeeId(@PathVariable Long taskId, @PathVariable Long employeeId) {
        var updateEmployeeId = UpdateTaskEmployeeIdCommandFromResourceAssembler.toCommandFromResource(taskId, employeeId);
        var updatedTask = activityCommandService.handle(updateEmployeeId);
        if (updatedTask.isEmpty()) return ResponseEntity.notFound().build();
        var updatedTaskEntity = updatedTask.get();
        var updatedTaskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(updatedTaskEntity);
        return ResponseEntity.ok(updatedTaskResource);
    }
}
