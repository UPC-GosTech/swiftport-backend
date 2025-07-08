package com.gostech.swiftportbackend.resources.domain.exceptions;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(Long teamId) {
        super("Team with id " + teamId + " not found");
    }
}
