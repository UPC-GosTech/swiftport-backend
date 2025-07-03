package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.commands.UpdateLocationStatusCommand;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.UpdateLocationStatusResource;

public class UpdateLocationStatusCommandFromResourceAssembler {
    public static UpdateLocationStatusCommand toCommandFromResource(Long locationId, UpdateLocationStatusResource resource) {
        return new UpdateLocationStatusCommand(
                locationId,
                resource.status()
        );
    }
}
