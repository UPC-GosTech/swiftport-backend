package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Zone;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateZoneCommand;

import java.util.Optional;

public interface ZoneCommandService {
    Optional<Zone> handle(CreateZoneCommand command);
}
