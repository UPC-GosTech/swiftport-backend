package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

public record CreatePositionResource (Long tenantId, String title, String description) {
    public CreatePositionResource {
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("tenantId cannot be null");
        }
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
    }
}
