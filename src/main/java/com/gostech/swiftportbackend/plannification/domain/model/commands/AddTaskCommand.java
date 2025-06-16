package com.gostech.swiftportbackend.plannification.domain.model.commands;

public record AddTaskCommand(Long activityId, String title, String description, String status) {
    public AddTaskCommand {
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
    }
}
