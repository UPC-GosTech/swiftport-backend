package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Position;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.PositionResource;

public class PositionResourceFromEntityAssembler {
    public static PositionResource toResourceFromEntity(Position entity) {
        return new PositionResource(
                entity.getId(),
                entity.getTenantId().getValue(),
                entity.getTitle(),
                entity.getDescription()
        );
    }
}
