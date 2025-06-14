package com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Position;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.PositionId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository {
    boolean existsByTitle(String title);
    Optional<Position> findByPositionId(PositionId positionId);
}
