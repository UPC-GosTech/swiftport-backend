package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

public record UpdateTaskDescriptionResource(String description) {
    public UpdateTaskDescriptionResource {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }
}
