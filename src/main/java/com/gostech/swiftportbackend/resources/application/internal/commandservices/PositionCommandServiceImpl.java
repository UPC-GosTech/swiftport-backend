package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.exceptions.PositionNotSavedException;
import com.gostech.swiftportbackend.resources.domain.exceptions.PositionTitleAlreadyExistsException;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Position;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreatePositionCommand;
import com.gostech.swiftportbackend.resources.domain.services.PositionCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.PositionRepository;
import com.gostech.swiftportbackend.shared.domain.exceptions.TenantNotFoundException;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;
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
            throw new PositionTitleAlreadyExistsException(command.title());

        Long tenantId = TenantContext.getCurrentTenantId();
        if (tenantId == null) {
            throw new TenantNotFoundException();
        }

        var position = new Position(tenantId, command);
        try {
            positionRepository.save(position);
        } catch (Exception ex) {
            throw new PositionNotSavedException(ex.getMessage());
        }
        return position.getId();
    }
}
