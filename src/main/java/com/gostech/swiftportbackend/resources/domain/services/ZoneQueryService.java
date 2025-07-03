package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import com.gostech.swiftportbackend.resources.domain.model.entities.Location;
import com.gostech.swiftportbackend.resources.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ZoneQueryService {
    Optional<Zone> handle(GetZoneByIdQuery query);
    List<Zone> handle(GetAllZonesQuery query);
    Optional<Location> handle(GetLocationByIdQuery query);
    List<Location> handle(GetAllLocationsQuery query);
    List<Location> handle(GetLocationsByZoneIdQuery query);
}
