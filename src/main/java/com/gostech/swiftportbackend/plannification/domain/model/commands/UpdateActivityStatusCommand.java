package com.gostech.swiftportbackend.plannification.domain.model.commands;

public record UpdateActivityStatusCommand(Long activityId, String activityStatus) {
    public UpdateActivityStatusCommand {
        if (activityId == null || activityId <= 0) {
            throw new IllegalArgumentException("Activity ID can't be null or empty");
        }
        if (activityStatus == null || activityStatus.isEmpty()) {
            throw new IllegalArgumentException("Activity status can't be null or empty");
        }
    }
}
