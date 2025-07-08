package com.gostech.swiftportbackend.resources.domain.exceptions;

public class TeamNameAlreadyExistsException extends RuntimeException {
    public TeamNameAlreadyExistsException(String message) {
        super("Team name already exists: " + message);
    }
}
