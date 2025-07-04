package com.gostech.swiftportbackend.resources.domain.model.entities;

import com.gostech.swiftportbackend.shared.domain.model.valueobjects.EmployeeId;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Team;
import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.domain.model.commands.AddTeamMemberCommand;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TeamMember extends AuditableModel {

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @NotNull
    private Team team;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    public TeamMember() {}

    public TeamMember(AddTeamMemberCommand command) {

    }

    public TeamMember(Team team, Employee employee) {
        this.team = team;
        this.employee = employee;
    }
}
