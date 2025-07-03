package com.gostech.swiftportbackend.execution.interfaces.rest.transform;

import com.gostech.swiftportbackend.execution.domain.model.commands.UpdateIncidentReportEmployeeIdCommand;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.UpdateIncidentReportEmployeeIdResource;

public class UpdateIncidentReportEmployeeIdCommandFromResourceAssembler {
    public static UpdateIncidentReportEmployeeIdCommand toCommandFromResource(Long incidentReportId, UpdateIncidentReportEmployeeIdResource resource) {
        return new UpdateIncidentReportEmployeeIdCommand(incidentReportId, resource.employeeId());
    }
}
