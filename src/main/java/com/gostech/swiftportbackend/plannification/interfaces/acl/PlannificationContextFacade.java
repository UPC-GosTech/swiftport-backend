package com.gostech.swiftportbackend.plannification.interfaces.acl;

import java.time.LocalDateTime;

public interface PlannificationContextFacade {
    Long createActivity(String activityCode, String description, LocalDateTime expectedTime, Integer weekNumber, String activityStatus, Long zoneOrigin, Long locationOrigin, Long zoneDestination, Long locationDestination);
}
