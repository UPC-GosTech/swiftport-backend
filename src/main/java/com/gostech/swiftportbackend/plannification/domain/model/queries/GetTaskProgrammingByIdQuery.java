package com.gostech.swiftportbackend.plannification.domain.model.queries;

public record GetTaskProgrammingByIdQuery(Long id) {
    public GetTaskProgrammingByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Task programming id cannot be null or negative");
        }
    }
}
