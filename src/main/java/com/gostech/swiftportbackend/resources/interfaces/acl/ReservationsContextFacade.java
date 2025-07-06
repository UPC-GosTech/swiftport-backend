package com.gostech.swiftportbackend.resources.interfaces.acl;

import java.time.LocalDateTime;

public interface ReservationsContextFacade {
    Long createReservation(String resourceType, Long resourceId, LocalDateTime start, LocalDateTime end);
    Long fetchReservationByResourceReference(Long resourceId, String resourceType);
    Boolean overlapsReservationDate(Long reservationId, LocalDateTime start, LocalDateTime end);
    Boolean updateReservationTimeInterval(Long reservationId, LocalDateTime start, LocalDateTime end);
}
