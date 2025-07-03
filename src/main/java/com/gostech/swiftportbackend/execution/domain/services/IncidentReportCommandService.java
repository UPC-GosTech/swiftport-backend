package com.gostech.swiftportbackend.execution.domain.services;

import com.gostech.swiftportbackend.execution.domain.model.commands.AddIncidentReportCommand;
import com.gostech.swiftportbackend.execution.domain.model.commands.UpdateIncidentReportDescriptionCommand;
import com.gostech.swiftportbackend.execution.domain.model.commands.UpdateIncidentReportEmployeeIdCommand;
import com.gostech.swiftportbackend.execution.domain.model.entities.IncidentReport;

import java.util.Optional;

public interface IncidentReportCommandService {
    Long handle(AddIncidentReportCommand command);
    Optional<IncidentReport> handle(UpdateIncidentReportDescriptionCommand command);
    Optional<IncidentReport> handle(UpdateIncidentReportEmployeeIdCommand command);
}
