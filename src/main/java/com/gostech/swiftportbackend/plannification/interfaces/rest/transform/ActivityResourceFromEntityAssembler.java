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
                entity.getActivityStatus(),
                entity.getZoneOrigin() != null ? entity.getZoneOrigin().getId() : null,
                entity.getLocationOrigin() != null ? entity.getLocationOrigin().getId() : null,
                entity.getZoneDestination() != null ? entity.getZoneDestination().getId() : null,
                entity.getLocationDestination() != null ? entity.getLocationDestination().getId() : null
        );
    }
}
