package com.gostech.swiftportbackend.execution.interfaces.rest.controllers;

import com.gostech.swiftportbackend.execution.domain.services.IncidentReportCommandService;
import com.gostech.swiftportbackend.execution.domain.services.IncidentReportQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/incident-report", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Incident Report", description = "Available Incident Report Endpoints")
public class IncidentReportController {
    private final IncidentReportCommandService incidentReportCommandService;
    private final IncidentReportQueryService incidentReportQueryService;

    public IncidentReportController(IncidentReportCommandService incidentReportCommandService, IncidentReportQueryService incidentReportQueryService) {
        this.incidentReportCommandService = incidentReportCommandService;
        this.incidentReportQueryService = incidentReportQueryService;
    }


}
