package com.gostech.swiftportbackend.execution.application.internal.queryservices;

import com.gostech.swiftportbackend.execution.domain.model.entities.IncidentReport;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetAllIncidentReportsQuery;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetIncidentReportByIdQuery;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetIncidentReportsByExecutionIdQuery;
import com.gostech.swiftportbackend.execution.domain.services.IncidentReportQueryService;
import com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories.ExecutionRepository;
import com.gostech.swiftportbackend.execution.infrastructure.persistence.jpa.repositories.IncidentReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentReportQueryServiceImpl implements IncidentReportQueryService {
    private final IncidentReportRepository incidentReportRepository;

    public IncidentReportQueryServiceImpl(IncidentReportRepository incidentReportRepository) {
        this.incidentReportRepository = incidentReportRepository;
    }

    @Override
    public Optional<IncidentReport> handle(GetIncidentReportByIdQuery query) {
        return incidentReportRepository.findById(query.id());
    }

    @Override
    public List<IncidentReport> handle(GetAllIncidentReportsQuery query) {
        return incidentReportRepository.findAll();
    }

    @Override
    public List<IncidentReport> handle(GetIncidentReportsByExecutionIdQuery query) {
        return incidentReportRepository.getByExecutionId(query.executionId());
    }
}
