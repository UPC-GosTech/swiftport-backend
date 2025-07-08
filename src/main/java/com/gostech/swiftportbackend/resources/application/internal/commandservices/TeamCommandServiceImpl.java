package com.gostech.swiftportbackend.resources.application.internal.commandservices;

import com.gostech.swiftportbackend.resources.domain.exceptions.*;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Team;
import com.gostech.swiftportbackend.resources.domain.model.commands.AddTeamMemberCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.CreateTeamCommand;
import com.gostech.swiftportbackend.resources.domain.model.commands.DeleteTeamMemberCommand;
import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;
import com.gostech.swiftportbackend.resources.domain.services.TeamCommandService;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.TeamMemberRepository;
import com.gostech.swiftportbackend.resources.infrastructure.persistence.jpa.repositories.TeamRepository;
import com.gostech.swiftportbackend.shared.domain.exceptions.TenantNotFoundException;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamCommandServiceImpl implements TeamCommandService {
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final EmployeeRepository employeeRepository;

    public TeamCommandServiceImpl(TeamRepository teamRepository, TeamMemberRepository teamMemberRepository, EmployeeRepository employeeRepository) {
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Long handle(CreateTeamCommand command) {
        if (teamRepository.existsByName(command.name()))
            throw new TeamNameAlreadyExistsException(command.name());

        Long tenantId = TenantContext.getCurrentTenantId();
        if (tenantId == null) {
            throw new TenantNotFoundException();
        }

        var team = new Team(tenantId, command);
        try {
            teamRepository.save(team);
        } catch (Exception e) {
            throw new TeamNotSavedException(e.getMessage());
        }
        return team.getId();
    }

    @Override
    public Long handle(AddTeamMemberCommand command) {
        Employee employee = employeeRepository.findById(command.employeeId())
                .orElseThrow(() -> new EmployeeNotFoundException(command.employeeId()));
        Team team = teamRepository.findById(command.teamId())
                .orElseThrow(() -> new TeamNotFoundException(command.teamId()));

        TeamMember member = new TeamMember(team, employee);

        try {
            member.setTeam(team);
            team.addMember(member);
            teamMemberRepository.save(member);
            teamRepository.save(team);
        } catch (Exception e) {
            throw new TeamMemberNotSavedException(e.getMessage());
        }
        return member.getId();
    }

    @Override
    public Long handle(DeleteTeamMemberCommand command) {
        TeamMember member = teamMemberRepository.findById(command.id())
                .orElseThrow(() -> new TeamMemberNotFoundException(command.teamId()));
        Team team = teamRepository.findById(command.teamId())
                .orElseThrow(() -> new TeamNotFoundException(command.teamId()));

        try {
            team.removeMember(member.getEmployee());
            teamRepository.save(team);
        } catch (Exception e) {
            throw new TeamMemberNotSavedException(e.getMessage());
        }
        return team.getId();
    }
}
