package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateReservationCommand;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateReservationResource;

public class CreateReservationCommandFromResourceAssembler {
    public static CreateReservationCommand toCommandFromResource(CreateReservationResource resource) {
        return new CreateReservationCommand(
                resource.tenantId(),
                resource.resourceType(),
                resource.resourceId(),
                resource.start(),
                resource.end()
        );
    }
}
