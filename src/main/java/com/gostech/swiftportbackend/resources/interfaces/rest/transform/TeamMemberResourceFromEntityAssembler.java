package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.TeamMemberResource;

public class TeamMemberResourceFromEntityAssembler {
    public static TeamMemberResource toResourceFromEntity(TeamMember entity) {
        return new TeamMemberResource(
                entity.getId(),
                entity.getTeam().getId(),
                entity.getEmployee() != null ? entity.getEmployee().getId() : null
        );
    }
}
