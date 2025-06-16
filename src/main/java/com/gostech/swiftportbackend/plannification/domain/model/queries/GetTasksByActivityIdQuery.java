package com.gostech.swiftportbackend.plannification.domain.model.queries;

public record GetTasksByActivityIdQuery(Long activityId) {
    public GetTasksByActivityIdQuery {
        if (activityId == null || activityId <= 0) {
            throw new IllegalArgumentException("Activity id cannot be null or negative");
        }
    }
}
