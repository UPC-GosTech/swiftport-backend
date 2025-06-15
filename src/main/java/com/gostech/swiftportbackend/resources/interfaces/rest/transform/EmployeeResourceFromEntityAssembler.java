package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.EmployeeResource;

public class EmployeeResourceFromEntityAssembler {
    public static EmployeeResource toResourceFromEntity(Employee entity) {
        return new EmployeeResource(entity.getId(), entity.getTenantId().getValue(), entity.getName().firstName(), entity.getName().lastName(), entity.getPosition(), entity.getEmployeeStatus(), entity.getContactInfo().email(), entity.getContactInfo().phoneNumber());
    }
}
