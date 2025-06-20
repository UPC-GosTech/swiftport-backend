package com.gostech.swiftportbackend.resources.domain.model.commands;

public record DeleteTeamMemberCommand(Long id, Long teamId) {
    public DeleteTeamMemberCommand {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Team member id cannot be null or less than zero");
        }
        if (teamId == null || teamId <= 0) {
            throw new IllegalArgumentException("Team member id cannot be null or less than zero");
        }
    }
}
