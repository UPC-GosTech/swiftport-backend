package com.gostech.swiftportbackend.execution.interfaces.rest.transform;

import com.gostech.swiftportbackend.execution.domain.model.commands.AddIncidentReportCommand;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.CreateIncidentReportResource;

public class AddIncidentReportCommandFromResourceAssembler {
    public static AddIncidentReportCommand toCommandFromResource(CreateIncidentReportResource resource) {
        return new AddIncidentReportCommand(
                resource.executionId(),
                resource.title(),
                resource.description(),
                resource.reportedAt(),
                resource.severity(),
                resource.tenantId()
        );
    }
}
