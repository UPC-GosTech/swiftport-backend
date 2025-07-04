package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.entities.Location;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.LocationResource;

public class LocationResourceFromEntityAssembler {
    public static LocationResource toResourceFromEntity(Location entity) {
        return new LocationResource(
                entity.getId(),
                entity.getZone().getId(),
                entity.getAddress().street(),
                entity.getAddress().city(),
                entity.getAddress().country(),
                entity.getCoordinates().latitude(),
                entity.getCoordinates().longitude(),
                entity.getLocationStatus()
        );
    }
}
