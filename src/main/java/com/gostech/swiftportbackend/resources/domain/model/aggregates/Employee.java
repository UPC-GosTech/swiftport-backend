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

    @Embedded
    private FullName name;

    @Embedded
    private ContactInfo contactInfo;

    private String position;

    @Embedded
    private Availability employeeStatus;

    public Employee(Long employeeId, Long tenantId, String name, String lastName, String position, String status, String email, String phoneNumber) {
        this.employeeId = new EmployeeId(employeeId);
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

    public Employee(CreateEmployeeCommand command) {
        this.employeeId = new EmployeeId(command.employeeId());
        this.tenantId = new TenantId(command.tenantId());
        this.name = new FullName(command.name(), command.lastName());
        this.contactInfo = new ContactInfo(command.email(), command.phoneNumber());
        this.position = command.position();
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

    public void changeAvailability(Availability availability) {
        this.employeeStatus = availability;
    }
}
