package com.gostech.swiftportbackend.resources.domain.model.entities;

import com.gostech.swiftportbackend.resources.domain.model.valueobjects.EmployeeId;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.TeamMemberId;
import com.gostech.swiftportbackend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class TeamMember extends AuditableModel {
    @NotNull
    @Embedded
    private TeamMemberId teamMemberId;

    @Embedded
    EmployeeId employeeId;

    public EmployeeId getEmployeeId() {
        return this.getEmployeeId();
    }
}
