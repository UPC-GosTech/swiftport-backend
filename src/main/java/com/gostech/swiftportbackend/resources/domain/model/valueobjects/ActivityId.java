package com.gostech.swiftportbackend.resources.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ActivityId(Long activityId) {
    public ActivityId {
        if (activityId == null || activityId < 1) {
            throw  new IllegalArgumentException("ActivityId cannot be null or less than 1");
        }
    }
}
