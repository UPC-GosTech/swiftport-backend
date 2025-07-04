package com.gostech.swiftportbackend.execution.interfaces.rest.transform;

import com.gostech.swiftportbackend.execution.domain.model.entities.IncidentReport;
import com.gostech.swiftportbackend.execution.interfaces.rest.resources.IncidentReportResource;

public class IncidentReportResourceFromEntityAssembler {
    public static IncidentReportResource toResourceFromEntity(IncidentReport entity) {
        return new IncidentReportResource(
                entity.getId(),
                entity.getExecution().getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getReportedAt(),
                entity.getSeverity(),
                entity.getTenantId().getValue()
        );
    }
}
