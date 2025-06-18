package com.gostech.swiftportbackend.execution.domain.services;

import com.gostech.swiftportbackend.execution.domain.model.entities.IncidentReport;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetAllIncidentReportsQuery;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetIncidentReportByIdQuery;
import com.gostech.swiftportbackend.execution.domain.model.queries.GetIncidentReportsByExecutionIdQuery;

import java.util.List;
import java.util.Optional;

public interface IncidentReportQueryService {
    Optional<IncidentReport> handle(GetIncidentReportByIdQuery query);
    List<IncidentReport> handle(GetAllIncidentReportsQuery query);
    List<IncidentReport> handle(GetIncidentReportsByExecutionIdQuery query);
}
