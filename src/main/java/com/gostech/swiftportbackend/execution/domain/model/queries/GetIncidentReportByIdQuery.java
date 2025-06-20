package com.gostech.swiftportbackend.execution.domain.model.queries;

public record GetIncidentReportByIdQuery(Long id) {
    public GetIncidentReportByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Incident Report ID cannot be null or less than 1");
        }
    }
}
