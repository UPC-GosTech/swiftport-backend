package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.ZoneResource;

public class ZoneResourceFromEntityAssembler {
    public static ZoneResource toResourceFromEntity(Zone entity) {
        return new ZoneResource(
                entity.getId(),
                entity.getTenantId().getValue(),
                entity.getName()
        );
    }
}
