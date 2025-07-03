package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.commands.UpdateEquipmentStatusCommand;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.UpdateEquipmentStatusResource;

public class UpdateEquipmentStatusCommandFromResourceAssembler {
    public static UpdateEquipmentStatusCommand toCommandFromResource(Long equipmentId, UpdateEquipmentStatusResource resource) {
        return new UpdateEquipmentStatusCommand(
                equipmentId,
                resource.status()
        );
    }
}
