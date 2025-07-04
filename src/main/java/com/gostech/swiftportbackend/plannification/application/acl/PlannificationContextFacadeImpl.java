package com.gostech.swiftportbackend.plannification.application.acl;

import com.gostech.swiftportbackend.execution.domain.services.ExecutionCommandService;
import com.gostech.swiftportbackend.plannification.domain.model.commands.CreateActivityCommand;
import com.gostech.swiftportbackend.plannification.domain.services.ActivityCommandService;
import com.gostech.swiftportbackend.plannification.domain.services.ActivityQueryService;
import com.gostech.swiftportbackend.plannification.interfaces.acl.PlannificationContextFacade;
import com.gostech.swiftportbackend.shared.infrastructure.multitenancy.TenantContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PlannificationContextFacadeImpl implements PlannificationContextFacade {
    private final ActivityCommandService activityCommandService;
    private final ActivityQueryService activityQueryService;

    public PlannificationContextFacadeImpl(ActivityCommandService activityCommandService, ActivityQueryService activityQueryService) {
        this.activityCommandService = activityCommandService;
        this.activityQueryService = activityQueryService;
    }

    public Long createActivity(
            String activityCode,
            String description,
            LocalDateTime expectedTime,
            Integer weekNumber,
            String activityStatus,
            Long zoneOrigin,
            Long locationOrigin,
            Long zoneDestination,
            Long locationDestination){

        var createActivityCommand = new CreateActivityCommand(
                activityCode,
                description,
                expectedTime,
                weekNumber,
                activityStatus,
                zoneOrigin,
                locationOrigin,
                zoneDestination,
                locationDestination
        );
        return activityCommandService.handle(createActivityCommand);
    }
}
