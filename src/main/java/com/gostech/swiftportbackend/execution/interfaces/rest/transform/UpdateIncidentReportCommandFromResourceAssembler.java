package com.gostech.swiftportbackend.execution.interfaces.rest.transform;

import com.gostech.swiftportbackend.execution.domain.model.commands.UpdateIncidentReportDescriptionCommand;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.UpdateIncidentReportResource;

public class UpdateIncidentReportCommandFromResourceAssembler {
    public static UpdateIncidentReportDescriptionCommand toCommandFromResource(UpdateIncidentReportResource resource) {
        return new UpdateIncidentReportDescriptionCommand(
                resource.incidentReportId(),
                resource.description()
        );
    }
}
