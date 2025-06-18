package com.gostech.swiftportbackend.execution.domain.model.queries;

public record GetExecutionByIdQuery(Long id) {
    public GetExecutionByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id cannot be null or less than 1");
        }
    }
}
