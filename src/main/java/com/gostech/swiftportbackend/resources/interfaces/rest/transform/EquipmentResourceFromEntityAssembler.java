package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Equipment;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.EquipmentResource;

public class EquipmentResourceFromEntityAssembler {
    public static EquipmentResource toResourceFromEntity(Equipment entity) {
        return new EquipmentResource(
                entity.getId(),
                entity.getTenantId().getValue(),
                entity.getName(),
                entity.getEquipmentStatus(),
                entity.getCode(),
                entity.getPlate(),
                entity.getCapacity().tons(),
                entity.getCapacity().passengers()
        );
    }
}
