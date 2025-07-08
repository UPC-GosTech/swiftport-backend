package com.gostech.swiftportbackend.plannification.domain.exceptions;

public class ActivityNotSavedException extends RuntimeException {
    public ActivityNotSavedException(String message) {
        super("Error saving activity: " + message);
    }
}
