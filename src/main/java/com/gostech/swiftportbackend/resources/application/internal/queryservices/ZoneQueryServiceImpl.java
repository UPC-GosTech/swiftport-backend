package com.gostech.swiftportbackend.resources.application.internal.queryservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import com.gostech.swiftportbackend.resources.domain.model.entities.Location;
import com.gostech.swiftportbackend.resources.domain.model.queries.*;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.Availability;
import com.gostech.swiftportbackend.resources.domain.services.ZoneQueryService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.LocationRepository;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneQueryServiceImpl implements ZoneQueryService {
    private final ZoneRepository zoneRepository;
    private final LocationRepository locationRepository;

    public ZoneQueryServiceImpl(ZoneRepository zoneRepository, LocationRepository locationRepository) {
        this.zoneRepository = zoneRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Optional<Zone> handle(GetZoneByIdQuery query) {
        return zoneRepository.findById(query.zoneId());
    }

    @Override
    public List<Zone> handle(GetAllZonesQuery query) {
        return zoneRepository.findAll();
    }

    @Override
    public Optional<Location> handle(GetLocationByIdQuery query) {
        return locationRepository.findById(query.id());
    }

    @Override
    public List<Location> handle(GetAllLocationsQuery query) {
        return locationRepository.findAll();
    }

    @Override
    public List<Location> handle(GetLocationsByZoneIdQuery query) {
        return locationRepository.findByZoneId(query.zoneId());
    }

    @Override
    public List<Location> handle(GetLocationsByStatusQuery query) {
        Availability status;
        switch (query.status()) {
            case "Available" -> status = Availability.AVAILABLE;
            case "Vacation" -> status = Availability.VACATION;
            case "Reserved" -> status = Availability.RESERVED;
            case "Unavailable" -> status = Availability.UNAVAILABLE;
            default -> {
                return locationRepository.findAll();
            }
        }
        return locationRepository.findByLocationStatus(status);
    }
}
