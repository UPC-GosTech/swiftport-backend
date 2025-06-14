package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Position;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreatePositionCommand;
import com.gostech.swiftportbackend.resources.domain.services.PositionCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.PositionRepository;
import org.springframework.stereotype.Service;

@Service
public class PositionCommandServiceImpl implements PositionCommandService {
    private final PositionRepository positionRepository;

    public PositionCommandServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Long handle(CreatePositionCommand command) {
        if (positionRepository.existsByTitle(command.title()))
            throw new IllegalArgumentException("Position with title %s already exists".formatted(command.title()));
        var position = new Position(command);
        try {
            positionRepository.save(position);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error saving position: %s".formatted(ex.getMessage()));
        }
        return position.getPositionId().positionId();
    }
}
