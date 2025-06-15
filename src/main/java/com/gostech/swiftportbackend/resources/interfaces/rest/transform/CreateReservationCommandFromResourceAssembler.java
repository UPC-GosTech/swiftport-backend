package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateReservationResource;

public class CreateReservationCommandFromResourceAssembler {
    public static CreateReservationResource toCommandResourceFromEntity(CreateReservationResource resource) {
        return new CreateReservationResource(
                resource.tenantId(),
                resource.resourceType(),
                resource.resourceId(),
                resource.start(),
                resource.end()
        );
    }
}
