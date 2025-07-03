package com.gostech.swiftportbackend.resources.interfaces.rest.transform;

import com.gostech.swiftportbackend.resources.domain.model.aggregates.Employee;
import com.gostech.swiftportbackend.resources.interfaces.rest.resources.EmployeeResource;

public class EmployeeResourceFromEntityAssembler {
    public static EmployeeResource toResourceFromEntity(Employee entity) {
        Long positionId = entity.getPosition() != null ? entity.getPosition().getId() : null;
        String positionTitle = entity.getPosition() != null ? entity.getPosition().getTitle() : null;
        return new EmployeeResource(
            entity.getTenantId().getValue(),
            entity.getName().firstName(),
            entity.getName().lastName(),
            positionId,
            positionTitle,
            entity.getEmployeeStatus(),
            entity.getContactInfo().email(),
            entity.getContactInfo().phoneNumber()
        );
    }
}
