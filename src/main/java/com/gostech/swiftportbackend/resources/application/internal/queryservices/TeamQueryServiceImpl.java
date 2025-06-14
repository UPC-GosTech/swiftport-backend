package com.gostech.swiftportbackend.resources.application.internal.queryservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Team;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllTeamsQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetEmployeeByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetTeamByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TeamId;
import com.gostech.swiftportbackend.resources.domain.services.TeamQueryService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamQueryServiceImpl implements TeamQueryService {
    private final TeamRepository teamRepository;

    public TeamQueryServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Optional<Team> handle(GetTeamByIdQuery query) {
        return teamRepository.findByTeamId(new TeamId(query.teamId()));
    }

    @Override
    public List<Team> handle(GetAllTeamsQuery query) {
        return teamRepository.findAll();
    }
}
