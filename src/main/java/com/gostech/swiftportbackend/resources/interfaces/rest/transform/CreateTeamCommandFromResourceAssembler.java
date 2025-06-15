package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateTeamCommand;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateTeamResource;

public class CreateTeamCommandFromResourceAssembler {
    public static CreateTeamCommand toCommandFromResource(CreateTeamResource resource) {
        return new CreateTeamCommand(
                resource.tenantId(),
                resource.name(),
                resource.teamMembers()
        );
    }
}