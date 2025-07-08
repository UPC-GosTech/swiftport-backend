package com.gostech.swiftportbackend.plannification.domain.exceptions;

public class ActivityNotFoundException extends RuntimeException {
    public ActivityNotFoundException(Long activityId) {
        super("Activity with id " + activityId + " not found");
    }
}
