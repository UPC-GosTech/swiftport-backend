package com.gostech.swiftportbackend.plannification.interfaces.rest.transform;

import com.gostech.swiftportbackend.plannification.domain.model.commands.UpdateActivityStatusCommand;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.UpdateActivityStatusResource;

public class UpdateActivityStatusCommandFromResourceAssembler {
    public static UpdateActivityStatusCommand toCommandFromResource(Long activityId, UpdateActivityStatusResource resource) {
        return new UpdateActivityStatusCommand(
                activityId,
                resource.status()
        );
    }
}
