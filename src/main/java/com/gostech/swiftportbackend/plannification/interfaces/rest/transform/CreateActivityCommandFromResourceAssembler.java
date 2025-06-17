package com.gostech.swiftportbackend.plannification.interfaces.rest.transform;

import com.gostech.swiftportbackend.plannification.domain.model.commands.CreateActivityCommand;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.CreateActivityResource;

public class CreateActivityCommandFromResourceAssembler {
    public static CreateActivityCommand toCommandFromResource(CreateActivityResource resource) {
        return new CreateActivityCommand(
                resource.activityCode(),
                resource.description(),
                resource.expectedTime(),
                resource.weekNumber(),
                resource.activityStatus(),
                resource.zoneOrigin(),
                resource.locationOrigin(),
                resource.zoneDestination(),
                resource.locationDestination(),
                resource.tenantId()
        );
    }
}
