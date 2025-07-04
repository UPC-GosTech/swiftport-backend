package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

public record CreatePositionResource (String title, String description) {
    public CreatePositionResource {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
    }
}
