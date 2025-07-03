package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Reservation;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.ReservationResource;

public class ReservationResourceFromEntityAssembler {
    public static ReservationResource toResourceFromEntity(Reservation entity) {
        return new ReservationResource(
                entity.getId(),
                entity.getTenantId(),
                entity.getResourceReference().resourceType(),
                entity.getResourceReference().resourceId(),
                entity.getTimeInterval().start(),
                entity.getTimeInterval().end()
        );
    }
}
