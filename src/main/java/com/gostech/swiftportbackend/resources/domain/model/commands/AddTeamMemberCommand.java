package com.gostech.swiftportbackend.resources.domain.model.commands;

public record AddTeamMemberCommand(Long employeeId, Long teamId) {
    public AddTeamMemberCommand {
        if (employeeId == null || employeeId <= 0) {
            throw new IllegalArgumentException("employeeId cannot be null");
        }
        if (teamId == null || teamId <= 0) {
            throw new IllegalArgumentException("teamId cannot be null");
        }
    }
}
