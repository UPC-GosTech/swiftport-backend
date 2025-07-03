package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

public record UpdateLocationStatusResource(String status) {
    public UpdateLocationStatusResource {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}
