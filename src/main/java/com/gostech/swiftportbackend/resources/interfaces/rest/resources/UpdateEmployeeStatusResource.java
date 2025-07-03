package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

public record UpdateEmployeeStatusResource(String status) {
    public UpdateEmployeeStatusResource {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
    }
}
