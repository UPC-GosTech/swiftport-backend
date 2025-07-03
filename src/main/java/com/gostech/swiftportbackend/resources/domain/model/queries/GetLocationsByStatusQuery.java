package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetLocationsByStatusQuery(String status) {
    public GetLocationsByStatusQuery {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("status cannot be null or empty");
        }
    }
}
