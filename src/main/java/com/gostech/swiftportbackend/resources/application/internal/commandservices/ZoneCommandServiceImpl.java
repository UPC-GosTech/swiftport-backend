package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.exceptions.*;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import com.gostech.swiftportbackend.resources.domain.model.commands.AddLocationCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateZoneCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.UpdateLocationStatusCommand;
import com.gostech.swiftportbackend.resources.domain.model.entities.Location;
import com.gostech.swiftportbackend.resources.domain.services.ZoneCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.LocationRepository;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.ZoneRepository;
import com.gostech.swiftportbackend.shared.domain.exceptions.TenantNotFoundException;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ZoneCommandServiceImpl implements ZoneCommandService {
    private final ZoneRepository zoneRepository;
    private final LocationRepository locationRepository;

    public ZoneCommandServiceImpl(ZoneRepository zoneRepository, LocationRepository locationRepository) {
        this.zoneRepository = zoneRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Long handle(CreateZoneCommand command) {
        if (zoneRepository.existsByName(command.name()))
            throw new ZoneNameNotFoundException(command.name());

        Long tenantId = TenantContext.getCurrentTenantId();
        if (tenantId == null) {
            throw new TenantNotFoundException();
        }

        var zone = new Zone(tenantId, command);
        try {
            zoneRepository.save(zone);
        } catch (Exception e) {
            throw new ZoneNotSavedException(e.getMessage());
        }
        return zone.getId();
    }

    @Override
    public Long handle(AddLocationCommand command) {
        Zone zone = zoneRepository.findById(command.zoneId())
                .orElseThrow(() -> new ZoneNotFoundException(command.zoneId()));
        var location = new Location(command);
        try {
            location.setZone(zone);
            zone.addLocation(location);
            locationRepository.save(location);
            zoneRepository.save(zone);
        } catch (Exception e) {
            throw new ZoneNotSavedException(e.getMessage());
        }
        return location.getId();
    }

    @Override
    public Optional<Location> handle(UpdateLocationStatusCommand command) {
        Location location = locationRepository.findById(command.locationId())
                .orElseThrow(() -> new LocationNotFoundException(command.locationId()));
        try {
            location.updateStatus(command.status());
            locationRepository.save(location);
        } catch (Exception e) {
            throw new LocationNotSavedException(e.getMessage());
        }
        return Optional.of(location);
    }
}
