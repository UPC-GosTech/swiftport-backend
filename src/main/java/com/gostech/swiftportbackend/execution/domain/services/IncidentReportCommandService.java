package com.gostech.swiftportbackend.execution.domain.services;

import com.gostech.swiftportbackend.execution.domain.model.commands.AddIncidentReportCommand;
import com.gostech.swiftportbackend.execution.domain.model.commands.UpdateIncidentReportDescriptionCommand;

public interface IncidentReportCommandService {
    Long handle(AddIncidentReportCommand command);
    Long handle(UpdateIncidentReportDescriptionCommand command);
}
