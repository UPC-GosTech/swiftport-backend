package com.gostech.swiftportbackend.resources.domain.exceptions;

public class PositionTitleAlreadyExistsException extends RuntimeException {
    public PositionTitleAlreadyExistsException(String message) {
        super("Position with title already exists: " + message);
    }
}
