package com.gostech.swiftportbackend.resources.domain.services;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Team;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllTeamsQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetTeamByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TeamQueryService {
    Optional<Team> handle(GetTeamByIdQuery query);
    List<Team> handle(GetAllTeamsQuery query);
}
