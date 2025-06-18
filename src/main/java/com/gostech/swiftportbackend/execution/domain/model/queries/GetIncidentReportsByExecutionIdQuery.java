package com.gostech.swiftportbackend.execution.domain.model.queries;

public record GetIncidentReportsByExecutionIdQuery(Long executionId) {
    public GetIncidentReportsByExecutionIdQuery {
        if (executionId == null || executionId <= 0) {
            throw new IllegalArgumentException("Execution ID cannot be null or less than 1");
        }
    }
}
