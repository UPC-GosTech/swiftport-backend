package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateZoneCommand;

public interface ZoneCommandService {
    Long handle(CreateZoneCommand command);
}
