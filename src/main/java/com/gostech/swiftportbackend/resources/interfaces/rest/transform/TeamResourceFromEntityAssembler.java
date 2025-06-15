package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Team;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.TeamResource;

public class TeamResourceFromEntityAssembler {
    public static TeamResource toResourceFromEntity(Team entity) {
        return new TeamResource(
                entity.getId(),
                entity.getTenantId().getValue(),
                entity.getName(),
                entity.getTeamMembers()
        );
    }
}
