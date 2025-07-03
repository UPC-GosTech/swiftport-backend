package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

public record UpdateActivityStatusResource(String status) {
    public UpdateActivityStatusResource {
        if (status == null || status.isEmpty()) {
            throw  new IllegalArgumentException("status cannot be null or empty");
        }
    }
}
