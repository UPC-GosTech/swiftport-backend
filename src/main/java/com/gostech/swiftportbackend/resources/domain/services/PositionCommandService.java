package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Position;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreatePositionCommand;

import java.util.Optional;

public interface PositionCommandService {
    Optional<Position> handle(CreatePositionCommand command);
}
