package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import com.gostech.swiftportbackend.resources.domain.model.commands.AddLocationCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateZoneCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.UpdateLocationStatusCommand;
import com.gostech.swiftportbackend.resources.domain.model.entities.Location;
import com.gostech.swiftportbackend.resources.domain.services.ZoneCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.LocationRepository;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.ZoneRepository;
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
            throw new IllegalArgumentException("Zone with name %s already exists".formatted(command.name()));

        Long tenantId = TenantContext.getCurrentTenantId();
        if (tenantId == null) {
            throw new RuntimeException("Tenant context not found");
        }

        var zone = new Zone(tenantId, command);
        try {
            zoneRepository.save(zone);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving zone: %s".formatted(e.getMessage()));
        }
        return zone.getId();
    }

    @Override
    public Long handle(AddLocationCommand command) {
        Zone zone = zoneRepository.findById(command.zoneId())
                .orElseThrow(() -> new IllegalArgumentException("Zone with id %s does not exist".formatted(command.zoneId())));
        var location = new Location(command);
        try {
            location.setZone(zone);
            zone.addLocation(location);
            locationRepository.save(location);
            zoneRepository.save(zone);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving zone: %s".formatted(e.getMessage()));
        }
        return location.getId();
    }

    @Override
    public Optional<Location> handle(UpdateLocationStatusCommand command) {
        Location location = locationRepository.findById(command.locationId())
                .orElseThrow(() -> new IllegalArgumentException("Location with id %s does not exist".formatted(command.locationId())));
        try {
            location.updateStatus(command.status());
            locationRepository.save(location);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving location: %s".formatted(e.getMessage()));
        }
        return Optional.of(location);
    }
}
