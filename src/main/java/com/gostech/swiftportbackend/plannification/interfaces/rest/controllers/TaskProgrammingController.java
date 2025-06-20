package com.gostech.swiftportbackend.plannification.interfaces.rest.controllers;

import com.gostech.swiftportbackend.plannification.domain.model.queries.GetTaskProgrammingByIdQuery;
import com.gostech.swiftportbackend.plannification.domain.model.queries.GetTaskProgrammingsByTaskIdQuery;
import com.gostech.swiftportbackend.plannification.domain.services.ActivityCommandService;
import com.gostech.swiftportbackend.plannification.domain.services.ActivityQueryService;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.CreateTaskProgrammingResource;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.TaskProgrammingResource;
import com.gostech.swiftportbackend.plannification.interfaces.rest.transform.AddTaskProgrammingCommandFromResourceAssembler;
import com.gostech.swiftportbackend.plannification.interfaces.rest.transform.TaskProgrammingResourceFromEntityAssembler;
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
@RequestMapping(value = "api/v1/task-programming", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Task Programming", description = "Task Programming Endpoints")
public class TaskProgrammingController {

    private final ActivityCommandService activityCommandService;
    private final ActivityQueryService activityQueryService;

    public TaskProgrammingController(ActivityCommandService activityCommandService, ActivityQueryService activityQueryService) {
        this.activityCommandService = activityCommandService;
        this.activityQueryService = activityQueryService;
    }

    @PostMapping
    @Operation(summary = "Add task programming", description = "Add programming to a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task programming created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Task not found")})
    public ResponseEntity<TaskProgrammingResource> addTaskProgramming(@RequestBody CreateTaskProgrammingResource resource) {
        var addTasProgrammingCommand = AddTaskProgrammingCommandFromResourceAssembler.toCommandFromResource(resource);
        var programmingId = activityCommandService.handle(addTasProgrammingCommand);
        if (programmingId == null || programmingId == 0L) return ResponseEntity.badRequest().build();
        var getTaskProgrammingByIdQuery = new GetTaskProgrammingByIdQuery(programmingId);
        var taskProgramming = activityQueryService.handle(getTaskProgrammingByIdQuery);
        if (taskProgramming.isEmpty()) return ResponseEntity.badRequest().build();
        var taskProgrammingEntity = taskProgramming.get();
        var taskProgrammingResource = TaskProgrammingResourceFromEntityAssembler.toResourceFromEntity(taskProgrammingEntity);
        return new ResponseEntity<>(taskProgrammingResource, HttpStatus.CREATED);
    }

    @GetMapping("/{programmingId}")
    @Operation(summary = "Get task programming by ID", description = "Get a single task programming by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task programming found"),
            @ApiResponse(responseCode = "404", description = "Task programming not found")})
    public ResponseEntity<TaskProgrammingResource> getTaskProgrammingById(@PathVariable Long programmingId) {
        var getTaskProgrammingByIdQuery = new GetTaskProgrammingByIdQuery(programmingId);
        var taskProgramming = activityQueryService.handle(getTaskProgrammingByIdQuery);
        if (taskProgramming.isEmpty()) return ResponseEntity.notFound().build();
        var taskProgrammingEntity = taskProgramming.get();
        var taskProgrammingResource = TaskProgrammingResourceFromEntityAssembler.toResourceFromEntity(taskProgrammingEntity);
        return ResponseEntity.ok(taskProgrammingResource);
    }

    @GetMapping("/tasks/{taskId}")
    @Operation(summary = "Get task programmings by task ID", description = "Get all task programmings for a given task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task programmings found"),
            @ApiResponse(responseCode = "404", description = "Task or task programmings not found")})
    public ResponseEntity<List<TaskProgrammingResource>> getTaskProgrammingsByTaskId(@PathVariable Long taskId) {
        var taskProgrammingList = activityQueryService.handle(new GetTaskProgrammingsByTaskIdQuery(taskId));
        if (taskProgrammingList.isEmpty()) return ResponseEntity.notFound().build();
        var resources = taskProgrammingList.stream()
                .map(TaskProgrammingResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
