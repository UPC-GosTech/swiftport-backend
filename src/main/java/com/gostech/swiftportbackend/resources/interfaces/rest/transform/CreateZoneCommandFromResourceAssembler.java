package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateZoneCommand;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateZoneResource;

public class CreateZoneCommandFromResourceAssembler {
    public static CreateZoneCommand toCommandFromResource(CreateZoneResource resource) {
        return new CreateZoneCommand(
                resource.tenantId(),
                resource.name(),
                resource.locations()
        );
    }
}
