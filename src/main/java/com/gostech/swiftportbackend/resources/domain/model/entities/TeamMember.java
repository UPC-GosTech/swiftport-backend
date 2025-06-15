package com.gostech.swiftportbackend.resources.domain.model.entities;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Team;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@NoArgsConstructor
public class TeamMember {

    private Long id;

    private Long employeeId;

    public TeamMember(Long employeeId, Team team) {
        this.employeeId = employeeId;
    }

}
