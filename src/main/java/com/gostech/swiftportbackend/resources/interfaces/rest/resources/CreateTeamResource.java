package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;

import java.util.List;

public record CreateTeamResource(Long tenantId, String name, List<TeamMember> teamMembers) {
    public CreateTeamResource {
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