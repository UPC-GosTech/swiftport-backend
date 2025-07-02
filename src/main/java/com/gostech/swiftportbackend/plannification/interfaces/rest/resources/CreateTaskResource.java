package com.gostech.swiftportbackend.plannification.interfaces.rest.resources;

public record CreateTaskResource(Long activityId, String description, String status, String title, Long employeeId) {
    public CreateTaskResource {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (activityId == null || activityId <= 0) {
            throw new IllegalArgumentException("Activity id cannot be null or zero or negative");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("Employee id cannot be null or zero or negative");
        }
    }
}
