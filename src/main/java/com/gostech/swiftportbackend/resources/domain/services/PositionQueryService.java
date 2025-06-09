package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Position;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllPositionsQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetPositionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PositionQueryService {
    Optional<Position> handle(GetPositionByIdQuery query);
    List<Position> handle(GetAllPositionsQuery query);
}
