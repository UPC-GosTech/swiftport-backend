package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import com.gostech.swiftportbackend.resources.domain.model.entities.Location;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllZonesQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetLocationByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetZoneByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ZoneQueryService {
    Optional<Zone> handle(GetZoneByIdQuery query);
    List<Zone> handle(GetAllZonesQuery query);
    Optional<Location> handle(GetLocationByIdQuery query);
}
