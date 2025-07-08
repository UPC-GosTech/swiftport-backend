package com.gostech.swiftportbackend.resources.domain.exceptions;

public class TeamMemberNotSavedException extends RuntimeException {
    public TeamMemberNotSavedException(String message) {
        super("Error saving team member: " + message);
    }
}
