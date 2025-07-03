package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.commands.AddLocationCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateZoneCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.UpdateLocationStatusCommand;
import com.gostech.swiftportbackend.resources.domain.model.entities.Location;

import java.util.Optional;

public interface ZoneCommandService {
    Long handle(CreateZoneCommand command);
    Long handle(AddLocationCommand command);
    Optional<Location> handle(UpdateLocationStatusCommand command);
}
