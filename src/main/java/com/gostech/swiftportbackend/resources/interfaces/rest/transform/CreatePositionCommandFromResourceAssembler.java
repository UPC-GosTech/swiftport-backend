package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreatePositionResource;

public class CreatePositionCommandFromResourceAssembler {
    public static CreatePositionResource toCommandResourceFromEntity(CreatePositionResource resource) {
        return new CreatePositionResource(
                resource.tenantId(),
                resource.title(),
                resource.description()
        );
    }
}
