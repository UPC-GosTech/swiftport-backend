package com.gostech.swiftportbackend.plannification.domain.model.queries;

public record GetActivityByIdQuery(Long id) {
    public GetActivityByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id cannot be null or less than zero");
        }
    }
}
