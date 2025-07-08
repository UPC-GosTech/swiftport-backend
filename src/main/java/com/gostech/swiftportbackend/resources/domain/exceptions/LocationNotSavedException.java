package com.gostech.swiftportbackend.resources.domain.exceptions;

public class LocationNotSavedException extends RuntimeException {
    public LocationNotSavedException(String message) {
        super("Error saving location: " + message);
    }
}
