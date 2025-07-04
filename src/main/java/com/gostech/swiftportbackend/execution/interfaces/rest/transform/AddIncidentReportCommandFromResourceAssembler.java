package com.gostech.swiftportbackend.execution.interfaces.rest.transform;

import com.gostech.swiftportbackend.execution.domain.model.commands.AddIncidentReportCommand;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.CreateIncidentReportResource;

public class AddIncidentReportCommandFromResourceAssembler {
    public static AddIncidentReportCommand toCommandFromResource(Long executionId, CreateIncidentReportResource resource) {
        return new AddIncidentReportCommand(
                executionId,
                resource.title(),
                resource.description(),
                resource.reportedAt(),
                resource.severity(),
                resource.employeeId()
        );
    }
}
