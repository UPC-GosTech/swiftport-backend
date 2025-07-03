package com.gostech.swiftportbackend.resources.domain.model.aggregates;

import com.gostech.swiftportbackend.resources.domain.model.commands.CreateEmployeeCommand;
import com.gostech.swiftportbackend.resources.domain.model.valueobjects.*;
import com.gostech.swiftportbackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.gostech.swiftportbackend.shared.domain.model.valueobjects.TenantId;
import com.gostech.swiftportbackend.resources.domain.model.entities.TeamMember;
import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Employee extends AuditableAbstractAggregateRoot<Employee> {

    @Embedded
    private TenantId tenantId;

    @Embedded
    private FullName name;

    @Embedded
    private ContactInfo contactInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position", referencedColumnName = "id")
    private Position position;

    @Embedded
    private Availability employeeStatus;

    public Employee(Long tenantId, String name, String lastName, Position position, String status, String email, String phoneNumber) {
        this.tenantId = new TenantId(tenantId);
        this.name = new FullName(name, lastName);
        this.contactInfo = new ContactInfo(email, phoneNumber);
        this.position = position;
        switch (status) {
            case "Available":
                this.employeeStatus = Availability.AVAILABLE;
                break;
            case "Vacation":
                this.employeeStatus = Availability.VACATION;
                break;
            case "Reserved":
                this.employeeStatus = Availability.RESERVED;
                break;
            default:
                this.employeeStatus = Availability.UNAVAILABLE;
                break;
        }
    }

    public Employee() {}

    public Employee(CreateEmployeeCommand command, Position position) {
        this.tenantId = new TenantId(command.tenantId());
        this.name = new FullName(command.name(), command.lastName());
        this.contactInfo = new ContactInfo(command.email(), command.phoneNumber());
        this.position = position;
        switch (command.employeeStatus()) {
            case "Available":
                this.employeeStatus = Availability.AVAILABLE;
                break;
            case "Vacation":
                this.employeeStatus = Availability.VACATION;
                break;
            case "Reserved":
                this.employeeStatus = Availability.RESERVED;
                break;
            default:
                this.employeeStatus = Availability.UNAVAILABLE;
                break;
        }
    }

    public void updateContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void updateStatus(String status) {
        switch (status) {
            case "Available":
                this.employeeStatus = Availability.AVAILABLE;
                break;
            case "Vacation":
                this.employeeStatus = Availability.VACATION;
                break;
            case "Reserved":
                this.employeeStatus = Availability.RESERVED;
                break;
            case "Unavailable":
                this.employeeStatus = Availability.UNAVAILABLE;
                break;
            default:
                break;
        }
    }
}
