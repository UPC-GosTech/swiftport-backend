package com.gostech.swiftportbackend.plannification.interfaces.rest.transform;

import com.gostech.swiftportbackend.plannification.domain.model.aggregates.Activity;
import com.gostech.swiftportbackend.plannification.interfaces.rest.resources.ActivityResource;

public class ActivityResourceFromEntityAssembler {
    public static ActivityResource toResourceFromEntity(Activity entity) {
        return new ActivityResource(
                entity.getId(),
                entity.getActivityCode().code(),
                entity.getDescription(),
                entity.getExpectedTime(),
                entity.getWeekNumber(),
                entity.getActivityStatus().toString(),
                entity.getOrigin().zoneId(),
                entity.getOrigin().locationId(),
                entity.getDestination().zoneId(),
                entity.getDestination().locationId(),
                entity.getTenantId().getValue()
        );
    }
}
