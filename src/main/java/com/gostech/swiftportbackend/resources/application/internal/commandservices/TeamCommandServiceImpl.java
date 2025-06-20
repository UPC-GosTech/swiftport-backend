package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Team;
import com.gostech.swiftportbackend.resources.domain.model.commands.AddTeamMemberCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateTeamCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.DeleteTeamMemberCommand;
import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;
import com.gostech.swiftportbackend.resources.domain.services.TeamCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.TeamMemberRepository;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamCommandServiceImpl implements TeamCommandService {
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    public TeamCommandServiceImpl(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository) {
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
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

    @Override
    public Optional<TeamMember> handle(DeleteTeamMemberCommand command) {
        TeamMember member = teamMemberRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Team member with id %s does not exist".formatted(command.id())));
        Team team = teamRepository.findById(command.teamId())
                .orElseThrow(() -> new IllegalArgumentException("Team with id %s does not exist".formatted(command.teamId())));
        try {
            member.setTeam(team);
            team.removeMember(member.getId());
            teamRepository.save(team);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving team member: %s".formatted(e.getMessage()));
        }
        return Optional.of(member);
    }
}
