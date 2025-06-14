package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateTeamCommand;
import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.EmployeeId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TeamId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TimeInterval;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Team extends AuditableAbstractAggregateRoot<Team> {

    @Embedded
    private TeamId teamId;

    @Embedded
    private TenantId tenantId;

    private String name;

    @ElementCollection
    @CollectionTable(name = "team_members", joinColumns = @JoinColumn(name = "team_id"))
    private List<TeamMember> teamMembers;

    public Team(Long TeamId, Long tenantId, String name, List<TeamMember> teamMembers) {
        this.teamId = new TeamId(TeamId);
        this.tenantId = new TenantId(tenantId);
        this.name = name;
        this.teamMembers = teamMembers;
    }

    public Team() {}

    public Team(CreateTeamCommand command) {
        this.teamId = new TeamId(command.teamId());
        this.tenantId = new TenantId(command.tenantId());
        this.name = command.name();
        this.teamMembers = command.teamMembers();
    }

    public void addMember(TeamMember teamMember) {
        this.teamMembers.add(teamMember);
    }

    public void removeMember(EmployeeId employeeId) {
        this.teamMembers.removeIf(member -> member.getEmployeeId().equals(employeeId));
    }

    public void validateMembersUnique() {
        /**
         * Ya aqui no se, tu mismo eres sergio
         */
    }
}
