package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreatePositionCommand;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreatePositionResource;

public class CreatePositionCommandFromResourceAssembler {
    public static CreatePositionCommand toCommandFromResource(CreatePositionResource resource) {
        return new CreatePositionCommand(
                resource.title(),
                resource.description()
        );
    }
}
