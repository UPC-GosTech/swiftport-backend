package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

public record UpdateTaskProgrammingStatusResource(String programmingStatus) {
    public UpdateTaskProgrammingStatusResource {
        if (programmingStatus == null || programmingStatus.isEmpty()) {
            throw new IllegalArgumentException("Programming status cannot be null or empty");
        }
    }
}
