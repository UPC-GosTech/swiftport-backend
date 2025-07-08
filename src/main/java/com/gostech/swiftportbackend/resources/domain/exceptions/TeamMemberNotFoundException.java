package com.gostech.swiftportbackend.resources.domain.exceptions;

public class TeamMemberNotFoundException extends RuntimeException {
    public TeamMemberNotFoundException(Long teamMemberId) {
        super("Team member with id " + teamMemberId + " not found");
    }
}
