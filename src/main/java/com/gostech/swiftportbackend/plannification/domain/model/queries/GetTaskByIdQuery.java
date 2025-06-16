package com.gostech.swiftportbackend.plannification.domain.model.queries;

public record GetTaskByIdQuery(Long id) {
    public GetTaskByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("task id cannot be null or negative");
        }
    }
}
