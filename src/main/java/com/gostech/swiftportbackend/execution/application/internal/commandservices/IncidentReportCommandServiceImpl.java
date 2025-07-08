package com.gostech.swiftportbackend.execution.application.internal.commandservices;

import com.gostech.swiftportbackend.execution.domain.exceptions.ExecutionNoFoundException;
import com.gostech.swiftportbackend.execution.domain.exceptions.IncidentReportNotFoundExceptions;
import com.gostech.swiftportbackend.execution.domain.exceptions.IncidentReportNotSavedException;
import com.gostech.swiftportbackend.execution.domain.exceptions.IncidentReportTitleAlreadyExistsException;
import com.gostech.swiftportbackend.execution.domain.model.aggregates.Execution;
import com.gostech.swiftportbackend.execution.domain.model.commands.AddIncidentReportCommand;
import com.gostech.swiftportbackend.execution.domain.model.commands.UpdateIncidentReportDescriptionCommand;
import com.gostech.swiftportbackend.execution.domain.model.commands.UpdateIncidentReportEmployeeIdCommand;
import com.gostech.swiftportbackend.execution.domain.model.entities.IncidentReport;
import com.gostech.swiftportbackend.execution.domain.services.IncidentReportCommandService;
import com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories.ExecutionRepository;
import com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories.IncidentReportRepository;
import com.gostech.swiftportbackend.shared.domain.exceptions.TenantNotFoundException;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncidentReportCommandServiceImpl implements IncidentReportCommandService {
    private final IncidentReportRepository incidentReportRepository;
    private final ExecutionRepository executionRepository;

    public  IncidentReportCommandServiceImpl(IncidentReportRepository incidentReportRepository, ExecutionRepository executionRepository) {
        this.incidentReportRepository = incidentReportRepository;
        this.executionRepository = executionRepository;
    }

    @Override
    public Long handle(AddIncidentReportCommand command) {
        if (incidentReportRepository.existsByTitle(command.title()))
            throw new IncidentReportTitleAlreadyExistsException(command.title());
        Execution execution = executionRepository.findById(command.executionId())
                .orElseThrow(() -> new ExecutionNoFoundException(command.executionId()));

        Long tenantId = TenantContext.getCurrentTenantId();
        if (tenantId == null) {
            throw new TenantNotFoundException();
        }

        var incident = new IncidentReport(tenantId, command);
        try {
            incident.setExecution(execution);
            execution.addIncidentReport(incident);
            incidentReportRepository.save(incident);
        } catch (Exception e) {
            throw new IncidentReportNotSavedException(e.getMessage());
        }
        return incident.getId();
    }

    @Override
    public Optional<IncidentReport> handle(UpdateIncidentReportDescriptionCommand command) {
        IncidentReport incidentReport = incidentReportRepository.findById(command.incidentReportId())
                .orElseThrow(() -> new IncidentReportNotFoundExceptions(command.incidentReportId()));
        incidentReport.updateDescription(command.description());
        try {
            incidentReportRepository.save(incidentReport);
        } catch (Exception e) {
            throw new IncidentReportNotSavedException(e.getMessage());
        }
        return Optional.of(incidentReport);
    }

    @Override
    public Optional<IncidentReport> handle(UpdateIncidentReportEmployeeIdCommand command) {
        IncidentReport incidentReport = incidentReportRepository.findById(command.incidentReportId())
                .orElseThrow(() -> new IncidentReportNotFoundExceptions(command.incidentReportId()));
        incidentReport.updateEmployeeId(command.employeeId());
        try {
            incidentReportRepository.save(incidentReport);
        } catch (Exception e) {
            throw new IncidentReportNotSavedException(e.getMessage());
        }
        return Optional.of(incidentReport);
    }
}
