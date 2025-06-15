package com.gostech.swiftportbackend.resources.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ReservationResource(Long reservationId, Long tenantId, String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end) {
}
