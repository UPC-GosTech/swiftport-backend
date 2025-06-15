package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreatePositionCommand;

public interface PositionCommandService {
    Long handle(CreatePositionCommand command);
}
