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
    private List<TeamMember> teamMembers;

    public Team(Long tenantId, String name) {
        this.name = name;
        this.teamMembers = new ArrayList<>();
    }

    public Team() {
        this.teamMembers = new ArrayList<>();
    }

    public Team(Long tenantId, CreateTeamCommand command) {
        this.name = command.name();
        this.tenantId = new TenantId(tenantId);
        this.teamMembers = new ArrayList<>();
    }

    public void addMember(TeamMember teamMember) {
        teamMember.setTeam(this);
        this.teamMembers.add(teamMember);
    }

    public void removeMember(Employee employee) {
        this.teamMembers.removeIf(member -> {
            if (member.getEmployee() != null && member.getEmployee().equals(employee)) {
                member.setTeam(null);
                return true;
            }
            return false;
        });
    }

}
