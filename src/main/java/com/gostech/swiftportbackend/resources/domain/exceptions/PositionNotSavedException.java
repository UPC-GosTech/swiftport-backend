package com.gostech.swiftportbackend.resources.domain.exceptions;

public class PositionNotSavedException extends RuntimeException {
    public PositionNotSavedException(String message) {
        super("Error on saving position: " + message);
    }
}
