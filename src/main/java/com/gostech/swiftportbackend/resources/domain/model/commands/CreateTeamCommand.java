package com.gostech.swiftportbackend.resources.domain.model.commands;

import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;

import java.util.List;

public record CreateTeamCommand(Long tenantId, String name, List<TeamMember> teamMembers) {
    public CreateTeamCommand {
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("tenantId cannot be null");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        if (teamMembers == null || teamMembers.isEmpty()) {
            throw new IllegalArgumentException("teamMembers cannot be null or empty");
        }
    }
}
