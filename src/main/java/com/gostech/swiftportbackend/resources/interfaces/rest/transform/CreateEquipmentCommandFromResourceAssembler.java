package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.interfaces.rest.resources.CreateEquipmentResource;

public class CreateEquipmentCommandFromResourceAssembler {
    public static CreateEquipmentResource toCommandResourceFromEntity(CreateEquipmentResource resource) {
        return new CreateEquipmentResource(
                resource.tenantId(),
                resource.name(),
                resource.status(),
                resource.code(),
                resource.plate(),
                resource.capacityLoad(),
                resource.capacityPax()
        );
    }
}
