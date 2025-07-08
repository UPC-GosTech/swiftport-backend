package com.gostech.swiftportbackend.plannification.domain.exceptions;

public class ActivityCodeAlreadyExistsException extends RuntimeException {
    public ActivityCodeAlreadyExistsException(String message) {
        super("Activity with code %s already exists".formatted(message));
    }
}
