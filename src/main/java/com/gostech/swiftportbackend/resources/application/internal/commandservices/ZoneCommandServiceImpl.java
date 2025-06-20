package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import com.gostech.swiftportbackend.resources.domain.model.commands.AddLocationCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateZoneCommand;
import com.gostech.swiftportbackend.resources.domain.model.entities.Location;
import com.gostech.swiftportbackend.resources.domain.services.ZoneCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.ZoneRepository;
import org.springframework.stereotype.Service;

@Service
public class ZoneCommandServiceImpl implements ZoneCommandService {
    private final ZoneRepository zoneRepository;

    public ZoneCommandServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Long handle(CreateZoneCommand command) {
        if (zoneRepository.existsByName(command.name()))
            throw new IllegalArgumentException("Zone with name %s already exists".formatted(command.name()));
        var zone = new Zone(command);
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
            zoneRepository.save(zone);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving zone: %s".formatted(e.getMessage()));
        }
        return location.getId();
    }
}
