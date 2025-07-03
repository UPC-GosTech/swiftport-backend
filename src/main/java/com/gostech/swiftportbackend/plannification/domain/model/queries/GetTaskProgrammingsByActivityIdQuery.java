package com.gostech.swiftportbackend.plannification.domain.model.queries;

public record GetTaskProgrammingsByActivityIdQuery(Long activityId) {
    public GetTaskProgrammingsByActivityIdQuery {
        if (activityId == null || activityId <= 0) {
            throw  new IllegalArgumentException("Activity ID cannot be null or less than zero");
        }
    }
}
