package com.gostech.swiftportbackend.resources.domain.exceptions;

public class ZoneNotSavedException extends RuntimeException {
    public ZoneNotSavedException(String message) {
        super("Error saving zone: " + message);
    }
}
