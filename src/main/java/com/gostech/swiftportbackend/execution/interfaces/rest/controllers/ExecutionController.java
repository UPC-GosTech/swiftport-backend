package com.gostech.swiftportbackend.execution.interfaces.rest.controllers;

import com.gostech.swiftportbackend.execution.domain.model.commands.AddEmployeeIdToExecutionCommand;
import com.gostech.swiftportbackend.execution.domain.model.commands.AddEquipmentIdToExecutionCommand;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetAllExecutionsQuery;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetExecutionByIdQuery;
import com.gostech.swiftportbackend.execution.domain.services.ExecutionCommandService;
import com.gostech.swiftportbackend.execution.domain.services.ExecutionQueryService;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.CreateExecutionResource;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.ExecutionResource;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.UpdateExecutionResource;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.UpdateTaskExecutionStatusResource;
import com.gostech.swiftportbackend.execution.interfaces.rest.transform.CreateExecutionCommandFromResourceAssembler;
import com.gostech.swiftportbackend.execution.interfaces.rest.transform.ExecutionResourceFromEntityAssembler;
import com.gostech.swiftportbackend.execution.interfaces.rest.transform.UpdateExecutionCommandFromResourceAssembler;
import com.gostech.swiftportbackend.execution.interfaces.rest.transform.UpdateTaskExecutionStatusCommandFromResourceAssembler;
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
@RequestMapping(value = "api/v1/executions", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Executions", description = "Available Execution Endpoints")
public class ExecutionController {
    private final ExecutionCommandService executionCommandService;
    private final ExecutionQueryService executionQueryService;

    public ExecutionController(ExecutionCommandService executionCommandService, ExecutionQueryService executionQueryService) {
        this.executionCommandService = executionCommandService;
        this.executionQueryService = executionQueryService;
    }

    @PostMapping
    @Operation(summary = "Create new execution", description = "Create new execution")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Execution created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Execution not found")
    })
    public ResponseEntity<ExecutionResource> createExecution(@RequestBody CreateExecutionResource resource) {
        var createExecutionCommand = CreateExecutionCommandFromResourceAssembler.toCommandFromResource(resource);
        var executionId = executionCommandService.handle(createExecutionCommand);
        if (executionId == null || executionId == 0L) return ResponseEntity.badRequest().build();

        var getExecutionByIdQuery = new GetExecutionByIdQuery(executionId);
        var execution = executionQueryService.handle(getExecutionByIdQuery);
        if (execution.isEmpty()) return ResponseEntity.badRequest().build();

        var executionEntity = execution.get();
        var executionResource = ExecutionResourceFromEntityAssembler.toResourceFromEntity(executionEntity);
        return new ResponseEntity<>(executionResource, HttpStatus.CREATED);
    }

    @GetMapping("/{executionId}")
    @Operation(summary = "Get execution by id", description = "Get execution by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Execution found"),
            @ApiResponse(responseCode = "404", description = "Execution not found")
    })
    public ResponseEntity<ExecutionResource> getExecutionById(@PathVariable Long executionId) {
        var getExecutionByIdQuery = new GetExecutionByIdQuery(executionId);
        var execution = executionQueryService.handle(getExecutionByIdQuery);
        if (execution.isEmpty()) return ResponseEntity.badRequest().build();
        var executionEntity = execution.get();
        var executionResource = ExecutionResourceFromEntityAssembler.toResourceFromEntity(executionEntity);
        return ResponseEntity.ok(executionResource);
    }

    @GetMapping
    @Operation(summary = "Get all executions", description = "Get all executions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Executions found"),
            @ApiResponse(responseCode = "404", description = "Executions not found")
    })
    public ResponseEntity<List<ExecutionResource>> getAllExecutions() {
        var executionList = executionQueryService.handle(new GetAllExecutionsQuery());
        if (executionList.isEmpty()) return ResponseEntity.badRequest().build();
        var executionResources = executionList.stream()
                .map(ExecutionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(executionResources);
    }

    @PostMapping("/{executionId}/employees/{employeeId}")
    @Operation(summary = "Add employee to execution", description = "Adds an employee ID to an existing execution")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee added to execution"),
            @ApiResponse(responseCode = "400", description = "Invalid execution or employee ID"),
            @ApiResponse(responseCode = "404", description = "Execution not found")
    })
    public ResponseEntity<ExecutionResource> addEmployeeToExecution(@PathVariable Long executionId, @PathVariable Long employeeId) {
        try {
            executionCommandService.handle(new AddEmployeeIdToExecutionCommand(executionId, employeeId));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
        return buildExecutionResponse(executionId);
    }

    @PostMapping("/{executionId}/equipment/{equipmentId}")
    @Operation(summary = "Add equipment to execution", description = "Adds an equipment ID to an existing execution")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipment added to execution"),
            @ApiResponse(responseCode = "400", description = "Invalid execution or equipment ID"),
            @ApiResponse(responseCode = "404", description = "Execution not found")
    })
    public ResponseEntity<ExecutionResource> addEquipmentToExecution(@PathVariable Long executionId, @PathVariable Long equipmentId) {
        try {
            executionCommandService.handle(new AddEquipmentIdToExecutionCommand(executionId, equipmentId));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
        return buildExecutionResponse(executionId);
    }

    private ResponseEntity<ExecutionResource> buildExecutionResponse(Long executionId) {
        var execution = executionQueryService.handle(new GetExecutionByIdQuery(executionId));
        if (execution.isEmpty()) return ResponseEntity.notFound().build();
        var executionResource = ExecutionResourceFromEntityAssembler.toResourceFromEntity(execution.get());
        return ResponseEntity.ok(executionResource);
    }

    @PutMapping("/{executionId}")
    @Operation(summary = "Update execution", description = "Updates all editable fields of an execution")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Execution updated"),
            @ApiResponse(responseCode = "404", description = "Execution not found")
    })
    public ResponseEntity<ExecutionResource> updateExecution(@RequestBody UpdateExecutionResource resource) {
        var updateExecutionCommand = UpdateExecutionCommandFromResourceAssembler.toCommandFromResource(resource);
        var updatedExecution = executionCommandService.handle(updateExecutionCommand);
        if (updatedExecution.isEmpty()) return ResponseEntity.notFound().build();
        var updatedExecutionEntity = updatedExecution.get();
        var updatedExecutionResource = ExecutionResourceFromEntityAssembler.toResourceFromEntity(updatedExecutionEntity);
        return ResponseEntity.ok(updatedExecutionResource);
    }

    @PatchMapping("/{executionId}/status")
    @Operation(summary = "Update execution status", description = "Updates only the task execution status of the execution")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Execution status updated"),
            @ApiResponse(responseCode = "404", description = "Execution not found")
    })
    public ResponseEntity<ExecutionResource> updateExecutionStatus(@RequestBody UpdateTaskExecutionStatusResource resource) {
        var updateStatusCommand = UpdateTaskExecutionStatusCommandFromResourceAssembler.toCommandFromResource(resource);
        var updatedExecution = executionCommandService.handle(updateStatusCommand);
        if (updatedExecution.isEmpty()) return ResponseEntity.notFound().build();
        var updatedExecutionEntity = updatedExecution.get();
        var updatedExecutionResource = ExecutionResourceFromEntityAssembler.toResourceFromEntity(updatedExecutionEntity);
        return ResponseEntity.ok(updatedExecutionResource);
    }
}
