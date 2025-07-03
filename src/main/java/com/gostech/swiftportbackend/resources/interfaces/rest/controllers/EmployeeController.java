package com.gostech.swiftportbackend.resources.interfaces.rest.controllers;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllEmployeesQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetEmployeeByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetEmployeesByStatusQuery;
import com.gostech.swiftportbackend.resources.domain.services.EmployeeCommandService;
import com.gostech.swiftportbackend.resources.domain.services.EmployeeQueryService;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateEmployeeResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.EmployeeResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.UpdateEmployeeStatusResource;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.CreateEmployeeCommandFromResourceAssembler;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.EmployeeResourceFromEntityAssembler;
import com.gostech.swiftportbackend.resources.interfaces.rest.transform.UpdateEmployeeStatusCommandFromResourceAssembler;
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
@RequestMapping(value = "api/v1/employees", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Employees", description = "Available Employees Endpoints")
public class EmployeeController {
    private final EmployeeCommandService employeeCommandService;
    private final EmployeeQueryService employeeQueryService;

    public EmployeeController(EmployeeCommandService employeeCommandService, EmployeeQueryService employeeQueryService) {
        this.employeeCommandService = employeeCommandService;
        this.employeeQueryService = employeeQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new employee", description = "Create a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Employee not found")})
    public ResponseEntity<EmployeeResource> createEmployee(@RequestBody CreateEmployeeResource resource) {
        System.out.println("Recibido: " + resource);
        var createEmployeeCommand = CreateEmployeeCommandFromResourceAssembler.toCommandFromResource(resource);
        var employeeId = employeeCommandService.handle(createEmployeeCommand);
        if (employeeId == null || employeeId == 0L) return ResponseEntity.badRequest().build();
        var getEmployeeByIdQuery = new GetEmployeeByIdQuery(employeeId);
        var employee = employeeQueryService.handle(getEmployeeByIdQuery);
        if (employee.isEmpty()) return ResponseEntity.notFound().build();
        var employeeEntity = employee.get();
        var employeeResource = EmployeeResourceFromEntityAssembler.toResourceFromEntity(employeeEntity);
        return new ResponseEntity<>(employeeResource, HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}")
    @Operation(summary = "Get employee by id", description = "Get employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "404", description = "Employee not found")})
    public ResponseEntity<EmployeeResource> getEmployee(@PathVariable Long employeeId) {
        var getEmployeeByIdQuery = new GetEmployeeByIdQuery(employeeId);
        var employee = employeeQueryService.handle(getEmployeeByIdQuery);
        if (employee.isEmpty()) return ResponseEntity.notFound().build();
        var employeeEntity = employee.get();
        var employeeResource = EmployeeResourceFromEntityAssembler.toResourceFromEntity(employeeEntity);
        return ResponseEntity.ok(employeeResource);
    }

    @GetMapping
    @Operation(summary = "Get all employees", description = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees found"),
            @ApiResponse(responseCode = "404", description = "Employee not found")})
    public ResponseEntity<List<EmployeeResource>> getAllEmployees() {
        var employees = employeeQueryService.handle(new GetAllEmployeesQuery());
        if (employees.isEmpty()) return ResponseEntity.notFound().build();
        var employeeResource = employees.stream()
                .map(EmployeeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(employeeResource);
    }

    @PatchMapping("/{employeeId}/status")
    @Operation(summary = "Update employee status", description = "Updates the status of an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee status updated"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    public ResponseEntity<EmployeeResource> updateEmployeeStatus(@PathVariable Long employeeId, @RequestBody UpdateEmployeeStatusResource resource) {
        var command = UpdateEmployeeStatusCommandFromResourceAssembler.toCommandFromResource(employeeId, resource);
        var updatedEmployee = employeeCommandService.handle(command);
        if (updatedEmployee.isEmpty()) return ResponseEntity.notFound().build();
        var employeeEntity = updatedEmployee.get();
        var employeeResource = EmployeeResourceFromEntityAssembler.toResourceFromEntity(employeeEntity);
        return ResponseEntity.ok(employeeResource);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get employees by status", description = "Get all employees with a specific availability status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees found"),
            @ApiResponse(responseCode = "404", description = "No employees found with the given status")
    })
    public ResponseEntity<List<EmployeeResource>> getEmployeesByStatus(@PathVariable String status) {
        var employees = employeeQueryService.handle(new GetEmployeesByStatusQuery(status));
        if (employees.isEmpty()) return ResponseEntity.notFound().build();
        var employeeResources = employees.stream()
                .map(EmployeeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(employeeResources);
    }

}
