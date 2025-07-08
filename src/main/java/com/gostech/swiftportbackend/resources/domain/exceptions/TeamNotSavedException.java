package com.gostech.swiftportbackend.resources.domain.exceptions;

public class TeamNotSavedException extends RuntimeException {
    public TeamNotSavedException(String message) {
        super("Error saving team: " + message);
    }
}
