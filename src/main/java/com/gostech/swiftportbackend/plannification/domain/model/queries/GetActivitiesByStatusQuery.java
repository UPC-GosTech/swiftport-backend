package com.gostech.swiftportbackend.plannification.domain.model.queries;

public record GetActivitiesByStatusQuery(String activityStatus) {
    public GetActivitiesByStatusQuery {
        if (activityStatus == null || activityStatus.isEmpty()) {
            throw  new IllegalArgumentException("Activity status cannot be null or empty");
        }
    }
}
