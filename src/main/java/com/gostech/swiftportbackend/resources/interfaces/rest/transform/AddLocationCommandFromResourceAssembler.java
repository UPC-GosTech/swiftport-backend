package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.commands.AddLocationCommand;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateLocationResource;

public class AddLocationCommandFromResourceAssembler {
    public static AddLocationCommand toCommandFromResource(CreateLocationResource resource) {
        return new AddLocationCommand(
                resource.zoneId(),
                resource.street(),
                resource.city(),
                resource.country(),
                resource.longitude(),
                resource.latitude()
        );
    }
}
