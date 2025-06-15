package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateZoneResource;

public class CreateZoneCommandFromResourceAssembler {
    public static CreateZoneResource toCommandResourceFromEntity(CreateZoneResource resource) {
        return new CreateZoneResource(
                resource.tenantId(),
                resource.name(),
                resource.locations()
        );
    }
}
