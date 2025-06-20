package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetLocationByIdQuery(Long id) {
    public GetLocationByIdQuery {
        if (id == null || id <= 0 ) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
