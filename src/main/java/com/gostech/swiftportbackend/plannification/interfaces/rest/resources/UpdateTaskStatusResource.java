package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

public record UpdateTaskStatusResource(String status) {
    public UpdateTaskStatusResource {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}
