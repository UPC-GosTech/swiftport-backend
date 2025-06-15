package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateZoneCommand;
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
}
