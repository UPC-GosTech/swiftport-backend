package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateTeamResource;

public class CreateTeamCommandFromResourceAssembler {
    public static CreateTeamResource toCommandResourceFromEntity(CreateTeamResource resource) {
        return new CreateTeamResource(
                resource.tenantId(),
                resource.name(),
                resource.teamMembers()
        );
    }
}