package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateTeamCommand;
import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Team extends AuditableAbstractAggregateRoot<Team> {

    @Embedded
    private TenantId tenantId;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamMember> teamMembers = new ArrayList<>();

    public Team(Long tenantId, String name, List<TeamMember> teamMembers) {
        this.tenantId = new TenantId(tenantId);
        this.name = name;
        this.teamMembers = teamMembers;
        this.teamMembers.forEach(member -> member.setTeam(this)); // asegura la relación bidireccional
    }

    public Team() {}

    public Team(CreateTeamCommand command) {
        this.tenantId = new TenantId(command.tenantId());
        this.name = command.name();
        this.teamMembers = command.teamMembers();
        this.teamMembers.forEach(member -> member.setTeam(this));
    }

    public void addMember(TeamMember teamMember) {
        teamMember.setTeam(this);
        this.teamMembers.add(teamMember);
    }

    public void removeMember(Long employeeId) {
        this.teamMembers.removeIf(member -> member.getEmployeeId().equals(employeeId));
    }

    public void validateMembersUnique() {
        // TODO: lógica para evitar duplicados
    }
}
