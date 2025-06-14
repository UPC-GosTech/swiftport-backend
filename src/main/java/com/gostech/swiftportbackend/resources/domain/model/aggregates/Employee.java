package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEmployeeCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.*;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Employee extends AuditableAbstractAggregateRoot<Employee> {

    @Embedded
    private EmployeeId employeeId;

    @Embedded
    private TenantId tenantId;

    private String name;

    @Embedded
    private PositionId positionId;

    @Embedded
    private EmployeeStatus employeeStatus;

    public Employee(Long employeeId, Long tenantId, String name, Long positionId, String status) {
        this.employeeId = new EmployeeId(employeeId);
        this.tenantId = new TenantId(tenantId);
        this.name = name;
        this.positionId = new PositionId(positionId);
        switch (status) {
            case "Available":
                this.employeeStatus = EmployeeStatus.AVAILABLE;
                break;
            case "Vacation":
                this.employeeStatus = EmployeeStatus.VACATION;
                break;
            default:
                this.employeeStatus = EmployeeStatus.UNAVAILABLE;
                break;
        }
    }

    public Employee() {}

    public Employee(CreateEmployeeCommand command) {
        this.employeeId = new EmployeeId(command.employeeId());
        this.tenantId = new TenantId(command.tenantId());
        this.name = command.name();
        this.positionId = new PositionId(command.positionId());
        switch (command.employeeStatus()) {
            case "Available":
                this.employeeStatus = EmployeeStatus.AVAILABLE;
                break;
            case "Vacation":
                this.employeeStatus = EmployeeStatus.VACATION;
                break;
            default:
                this.employeeStatus = EmployeeStatus.UNAVAILABLE;
                break;
        }
    }

    public boolean isAvailable(TimeInterval timeInterval) {
        return this.employeeStatus == EmployeeStatus.AVAILABLE;

        /**
         * MISSING PART OF THE LOGIC
         */

    }
}
