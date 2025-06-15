package com.gostech.swiftportbackend.resources.domain.model.entities;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Team;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.EmployeeId;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "team_members")
@NoArgsConstructor
public class TeamMember extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private EmployeeId employeeId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public TeamMember(EmployeeId employeeId, Team team) {
        this.employeeId = employeeId;
        this.team = team;
    }

}
