package com.gostech.swiftportbackend.resources.domain.model.queries;

public record GetEmployeesByStatusQuery(String status) {
    public GetEmployeesByStatusQuery {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("status cannot be null or empty");
        }
    }
}
