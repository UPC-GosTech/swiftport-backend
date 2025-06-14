package com.gostech.swiftportbackend.resources.application.internal.queryservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Position;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllPositionsQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetPositionByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.PositionId;
import com.gostech.swiftportbackend.resources.domain.services.PositionQueryService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionQueryServiceImpl implements PositionQueryService {
    private final PositionRepository positionRepository;

    public PositionQueryServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Optional<Position> handle(GetPositionByIdQuery query) {
        return positionRepository.findByPositionId(new PositionId(query.positionId()));
    }

    @Override
    public List<Position> handle(GetAllPositionsQuery query) {
        return positionRepository.findAll();
    }
}
