package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Team;
import com.gostech.swiftportbackend.resources.domain.model.commands.AddTeamMemberCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateTeamCommand;
import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;
import com.gostech.swiftportbackend.resources.domain.services.TeamCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamCommandServiceImpl implements TeamCommandService {
    private final TeamRepository teamRepository;

    public TeamCommandServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Long handle(CreateTeamCommand command) {
        if (teamRepository.existsByName(command.name()))
            throw new IllegalArgumentException("Team with name %s already exists".formatted(command.name()));
        var team = new Team(command);
        try {
            teamRepository.save(team);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving team: %s".formatted(e.getMessage()));
        }
        return team.getId();
    }

    @Override
    public Long handle(AddTeamMemberCommand command) {
        Team team = teamRepository.findById(command.teamId())
                .orElseThrow(() -> new IllegalArgumentException("Team with id %s does not exist".formatted(command.teamId())));
        var member = new TeamMember(command);
        try {
            member.setTeam(team);
            team.addMember(member);
            teamRepository.save(team);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving team member: %s".formatted(e.getMessage()));
        }
        return member.getId();
    }
}
