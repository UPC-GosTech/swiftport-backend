package com.gostech.swiftportbackend.execution.application.internal.commandservices;

import com.gostech.swiftportbackend.execution.domain.model.aggregates.Execution;
import com.gostech.swiftportbackend.execution.domain.model.commands.AddIncidentReportCommand;
import com.gostech.swiftportbackend.execution.domain.model.commands.UpdateIncidentReportDescriptionCommand;
import com.gostech.swiftportbackend.execution.domain.model.entities.IncidentReport;
import com.gostech.swiftportbackend.execution.domain.services.IncidentReportCommandService;
import com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories.ExecutionRepository;
import com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories.IncidentReportRepository;
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
            throw new IllegalStateException("Incident report with title %s already exists".formatted(command.title()));
        Execution execution = executionRepository.findById(command.executionId())
                .orElseThrow(() -> new IllegalArgumentException("Execution not found"));
        var incident = new IncidentReport(command);
        try {
            incident.setExecution(execution);
            execution.addIncidentReport(incident);
            incidentReportRepository.save(incident);
        } catch (Exception e) {
            throw new IllegalStateException("Error saving incident report: %s".formatted(e.getMessage()));
        }
        return incident.getId();
    }

    @Override
    public Optional<IncidentReport> handle(UpdateIncidentReportDescriptionCommand command) {
        IncidentReport incidentReport = incidentReportRepository.findById(command.incidentReportId())
                .orElseThrow(() -> new IllegalArgumentException("Incident report not found"));
        incidentReport.updateDescription(command.description());
        try {
            incidentReportRepository.save(incidentReport);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving incident report: %s".formatted(e.getMessage()));
        }
        return Optional.of(incidentReport);
    }
}
