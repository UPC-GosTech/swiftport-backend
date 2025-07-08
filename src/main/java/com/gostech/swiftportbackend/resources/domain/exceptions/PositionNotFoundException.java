package com.gostech.swiftportbackend.resources.domain.exceptions;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(Long positionId) {
        super("Position with id " + positionId + " not found");
    }
}
