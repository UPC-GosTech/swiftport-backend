package com.gostech.swiftportbackend.plannification.domain.model.queries;

public record GetTasksByStatusQuery(String status) {
    public GetTasksByStatusQuery {
        if (status == null || status.isEmpty())
            throw new IllegalArgumentException("Status cannot be null or empty");
    }
}
