package com.gostech.swiftportbackend.execution.interfaces.rest.controllers;

import com.gostech.swiftportbackend.execution.domain.model.queries.GetAllIncidentReportsQuery;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetIncidentReportByIdQuery;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetIncidentReportsByExecutionIdQuery;
import com.gostech.swiftportbackend.execution.domain.services.IncidentReportCommandService;
import com.gostech.swiftportbackend.execution.domain.services.IncidentReportQueryService;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.CreateIncidentReportResource;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.IncidentReportResource;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.UpdateIncidentReportResource;
import com.gostech.swiftportbackend.execution.interfaces.rest.transform.AddIncidentReportCommandFromResourceAssembler;
import com.gostech.swiftportbackend.execution.interfaces.rest.transform.IncidentReportResourceFromEntityAssembler;
import com.gostech.swiftportbackend.execution.interfaces.rest.transform.UpdateIncidentReportCommandFromResourceAssembler;
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
@RequestMapping(value = "api/v1/incident-reports", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Incident Reports", description = "Available Incident Report Endpoints")
public class IncidentReportController {
    private final IncidentReportCommandService incidentReportCommandService;
    private final IncidentReportQueryService incidentReportQueryService;

    public IncidentReportController(IncidentReportCommandService incidentReportCommandService, IncidentReportQueryService incidentReportQueryService) {
        this.incidentReportCommandService = incidentReportCommandService;
        this.incidentReportQueryService = incidentReportQueryService;
    }

    @PostMapping("/{executionId}/incidents")
    @Operation(summary = "Add incident report to execution", description = "Creates a new incident report and adds it to the given execution")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Incident report added to execution"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Execution not found")
    })
    public ResponseEntity<IncidentReportResource> addIncidentReportToExecution(@RequestBody CreateIncidentReportResource resource) {
        var command = AddIncidentReportCommandFromResourceAssembler.toCommandFromResource(resource);
        var incidentReportId = incidentReportCommandService.handle(command);
        if (incidentReportId == null || incidentReportId == 0L) return ResponseEntity.badRequest().build();
        var query = new GetIncidentReportByIdQuery(incidentReportId);
        var incidentReport = incidentReportQueryService.handle(query);
        if (incidentReport.isEmpty()) return ResponseEntity.notFound().build();
        var entity = incidentReport.get();
        var incidentResource = IncidentReportResourceFromEntityAssembler.toResourceFromEntity(entity);
        return new ResponseEntity<>(incidentResource, HttpStatus.CREATED);
    }

    @GetMapping("/incidents/{incidentId}")
    @Operation(summary = "Get incident report by ID", description = "Retrieve a specific incident report by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incident report found"),
            @ApiResponse(responseCode = "404", description = "Incident report not found")
    })
    public ResponseEntity<IncidentReportResource> getIncidentReportById(@PathVariable Long incidentId) {
        var query = new GetIncidentReportByIdQuery(incidentId);
        var incident = incidentReportQueryService.handle(query);
        if (incident.isEmpty()) return ResponseEntity.notFound().build();
        var incidentEntity = incident.get();
        var resource = IncidentReportResourceFromEntityAssembler.toResourceFromEntity(incidentEntity);
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{executionId}/incidents")
    @Operation(summary = "Get all incident reports by execution ID", description = "Retrieve all incident reports related to a given execution")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incident reports found"),
            @ApiResponse(responseCode = "404", description = "No incident reports found for execution")
    })
    public ResponseEntity<List<IncidentReportResource>> getIncidentReportsByExecutionId(@PathVariable Long executionId) {
        var query = new GetIncidentReportsByExecutionIdQuery(executionId);
        var incidentList = incidentReportQueryService.handle(query);
        if (incidentList.isEmpty()) return ResponseEntity.notFound().build();
        var resources = incidentList.stream()
                .map(IncidentReportResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/incidents")
    @Operation(summary = "Get all incident reports", description = "Retrieve all incident reports in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incident reports found"),
            @ApiResponse(responseCode = "404", description = "No incident reports found")
    })
    public ResponseEntity<List<IncidentReportResource>> getAllIncidentReports() {
        var incidentList = incidentReportQueryService.handle(new GetAllIncidentReportsQuery());
        if (incidentList.isEmpty()) return ResponseEntity.notFound().build();
        var resources = incidentList.stream()
                .map(IncidentReportResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/incidents/{incidentReportId}")
    @Operation(summary = "Update incident report", description = "Update the description of an existing incident report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incident report updated"),
            @ApiResponse(responseCode = "404", description = "Incident report not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<IncidentReportResource> updateIncidentReport(@RequestBody UpdateIncidentReportResource resource) {
        var command = UpdateIncidentReportCommandFromResourceAssembler.toCommandFromResource(resource);
        var updatedIncident = incidentReportCommandService.handle(command);
        if (updatedIncident.isEmpty()) return ResponseEntity.notFound().build();
        var incidentEntity = updatedIncident.get();
        var incidentResource = IncidentReportResourceFromEntityAssembler.toResourceFromEntity(incidentEntity);
        return ResponseEntity.ok(incidentResource);
    }
}
