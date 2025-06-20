package com.gostech.swiftportbackend.resources.application.internal.queryservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Team;
import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetAllTeamsQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetTeamByIdQuery;
import com.gostech.swiftportbackend.resources.domain.model.queries.GetTeamMemberByIdQuery;
import com.gostech.swiftportbackend.resources.domain.services.TeamQueryService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.TeamMemberRepository;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamQueryServiceImpl implements TeamQueryService {
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    public TeamQueryServiceImpl(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository) {
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    @Override
    public Optional<Team> handle(GetTeamByIdQuery query) {
        return teamRepository.findById(query.teamId());
    }

    @Override
    public List<Team> handle(GetAllTeamsQuery query) {
        return teamRepository.findAll();
    }

    @Override
    public Optional<TeamMember> handle(GetTeamMemberByIdQuery query) {
        return teamMemberRepository.findById(query.id());
    }
}
