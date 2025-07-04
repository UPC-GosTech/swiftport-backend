package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEquipmentCommand;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateEquipmentResource;

public class CreateEquipmentCommandFromResourceAssembler {
    public static CreateEquipmentCommand toCommandFromResource(CreateEquipmentResource resource) {
        return new CreateEquipmentCommand(
                resource.name(),
                resource.status(),
                resource.code(),
                resource.plate(),
                resource.capacityLoad(),
                resource.capacityPax()
        );
    }
}
